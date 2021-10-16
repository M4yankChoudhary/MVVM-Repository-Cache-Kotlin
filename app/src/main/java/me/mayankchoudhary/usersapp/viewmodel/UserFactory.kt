package me.mayankchoudhary.usersapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.mayankchoudhary.usersapp.room.UserDao
import java.lang.IllegalArgumentException

// its a boiler plate code reuse it whenever you need
class UserFactory(private val userDao: UserDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        Check if the modelClass is the same
//        as the InventoryViewModel class
//        and return an instance of it.
//        Otherwise, throw an exception.
        if (modelClass.isAssignableFrom(UsersListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UsersListViewModel(userDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}