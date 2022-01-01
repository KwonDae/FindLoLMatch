package com.example.testriotapi.db

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * @author Daewon
 * @package com.example.testriotapi.db
 * @email green201402317@gmail.com
 * @created 2022/01/01
 */

@Database(
    entities = [User::class],
    version = 1
)
abstract class UserDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDAO
}