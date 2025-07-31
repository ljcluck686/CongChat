package com.software.mywechat.core.data.repository

import com.software.mywechat.core.database.dao.FriendDao
import com.software.mywechat.core.database.model.FriendEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FriendRepository @Inject constructor(
    private val friendDao: FriendDao,
) {
    fun getAll(): Flow<List<FriendEntity>> = friendDao.getAll()

    suspend fun insert(data: FriendEntity) = friendDao.insert(data)

    suspend fun delete(data: FriendEntity) = friendDao.delete(data)

    suspend fun update(data:FriendEntity) = friendDao.update(data)

    fun getFriendList(): Flow<List<FriendEntity>> = friendDao.getFriendList()

    fun getFriend(id:String) : FriendEntity = friendDao.getFriend(id)

}