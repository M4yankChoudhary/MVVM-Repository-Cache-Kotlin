package me.mayankchoudhary.usersapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val uid: String,
    val avatar: String
) {

}