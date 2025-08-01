package com.software.mywechat.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.software.mywechat.core.database.model.FriendEntity
import com.software.mywechat.core.database.model.MyFriendEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MyFriendDao {
    @Query("SELECT * FROM my_friend_list order by nickname desc")
    fun getAll(): Flow<List<MyFriendEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: MyFriendEntity)

    @Delete
    suspend fun delete(data: MyFriendEntity)

}