package me.mayankchoudhary.usersapp

import android.app.Application
import me.mayankchoudhary.usersapp.room.UserDatabase

class UserApplication : Application() {
    val database: UserDatabase by lazy {
        UserDatabase.getDatabase(this)
    }
}