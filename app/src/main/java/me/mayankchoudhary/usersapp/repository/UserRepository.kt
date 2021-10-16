package me.mayankchoudhary.usersapp.repository

import androidx.lifecycle.*
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import me.mayankchoudhary.usersapp.model.User
import me.mayankchoudhary.usersapp.network.UserAPIObject
import me.mayankchoudhary.usersapp.room.UserDao
import me.mayankchoudhary.usersapp.room.UserDatabase

class UserRepository(private val userDao: UserDao) {

    // get data from local db
//    val users: LiveData<List<User>> =


//    suspend fun getAllUsersFromDB(): LiveData<List<User>> {
//
//    }
    suspend fun refreshVideos() {
        withContext(Dispatchers.IO) {
//            userDao.deleteUsers()
            val usersList = UserAPIObject.retrofitService.getUsers()
            userDao.insertUser(usersList)
        }
    }
}