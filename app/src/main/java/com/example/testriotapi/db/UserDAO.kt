package com.example.testriotapi.db

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * @author Daewon
 * @package com.example.testriotapi.db
 * @email green201402317@gmail.com
 * @created 2022/01/01
 */

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY timestamp DESC")
    suspend fun getAllUserSortedByDate(): MutableList<User>
}