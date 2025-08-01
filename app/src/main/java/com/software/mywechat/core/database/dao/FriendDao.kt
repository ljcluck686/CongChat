package com.software.mywechat.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.software.mywechat.core.database.model.FriendEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FriendDao {
    @Query("SELECT * FROM friend_list order by nickname desc")
    fun getAll(): Flow<List<FriendEntity>>

    @Query("SELECT * FROM friend_list WHERE status == 1 ORDER BY nickname DESC")
    fun getFriendList():Flow<List<FriendEntity>>

    @Query("SELECT * FROM friend_list WHERE userId = :id ORDER BY nickname DESC")
    suspend fun getFriend(id: String): FriendEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: FriendEntity)

    @Delete
    suspend fun delete(data: FriendEntity)

    @Update
    suspend fun update(data: FriendEntity)


}