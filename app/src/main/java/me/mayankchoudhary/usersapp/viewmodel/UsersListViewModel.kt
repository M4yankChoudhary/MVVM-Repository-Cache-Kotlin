package me.mayankchoudhary.usersapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import me.mayankchoudhary.usersapp.model.User
import me.mayankchoudhary.usersapp.network.UserAPIObject
import me.mayankchoudhary.usersapp.repository.UserRepository
import me.mayankchoudhary.usersapp.room.UserDao
import java.io.IOException
import java.lang.IllegalArgumentException

class UsersListViewModel(userDao: UserDao) : ViewModel() {

    val allItems: LiveData<List<User>> = userDao.getAllUsers().asLiveData()
//        .asLiveData()
    val userRepository = UserRepository(userDao)
//    private val _photos = MutableLiveData<List<User>>()
    private val _uid = MutableLiveData<List<User>>()
    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    val photos: LiveData<List<User>> = allItems
    val uid: LiveData<List<User>> = _uid


    /**
     * Flag to display the error message. This is private to avoid exposing a
     * way to set this value to observers.
     */
//    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    /**
     * Flag to display the error message. Views should use this to get access
     * to the data.
     */
//    val isNetworkErrorShown: LiveData<Boolean>
//        get() = _isNetworkErrorShown


    init {
        getPhotos()
        getUid()
    }

    private fun getUid() {
        viewModelScope.launch {
            try {
                _uid.value = UserAPIObject.retrofitService.getUsers()
//                Log.d("UserError", _photos.value.toString())
                _eventNetworkError.value = false
//                _isNetworkErrorShown.value = false
            } catch (e: Exception) {
                Log.d("UserError", e.toString())
//                if (_photos.value.isNullOrEmpty()) {
//                    _eventNetworkError.value = true
//                }
            }
        }
    }

    /**
     * Resets the network error flag.
     */
//    fun onNetworkErrorShown() {
//        _isNetworkErrorShown.value = true
//    }

    fun getPhotos() {
        viewModelScope.launch {
            try {
//                userDao.deleteUsers()
                userRepository.refreshVideos()
//                _photos.value = UserAPIObject.retrofitService.getUsers()
//                Log.d("UserError", _photos.value.toString())
                _eventNetworkError.value = false
//                _isNetworkErrorShown.value = false
            } catch (e: IOException) {
//                Log.d("UserError", e.toString())
//                allItems.value = listOf()
                if (allItems.value.isNullOrEmpty()) {
                    _eventNetworkError.value = true
                }
            }
        }
    }

//    class Factory(val app: Application) : ViewModelProvider.Factory {
//        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//            if (modelClass.isAssignableFrom(UsersListViewModel::class.java)) {
//                @Suppress("UNCHECKED_CAST")
//                return UsersListViewModel(app) as T
//            }
//            throw IllegalArgumentException("Unable to construct viewmodel")
//        }
//    }

}


