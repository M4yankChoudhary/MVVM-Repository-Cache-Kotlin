package me.mayankchoudhary.usersapp.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import me.mayankchoudhary.usersapp.model.User
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.converter.moshi.MoshiConverterFactory

// add constant BaseURL
private  const val BASE_URL =
    "https://random-data-api.com/api/"
/**
 * Build the Moshi object that Retrofit will be using,
 * making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Use the Retrofit builder to build a
 * retrofit object using a Moshi
 * converter with our Moshi
 * object.
 */
private val retrofit =  Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/**
 * A public interface that exposes the [getUsers] method
 */
interface UsersAPI {
    companion object {

    }

    @GET("users/random_user?size=20")
    suspend fun getUsers(): List<User>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object UserAPIObject {
    val retrofitService: UsersAPI by lazy { retrofit.create(UsersAPI::class.java) }
}
