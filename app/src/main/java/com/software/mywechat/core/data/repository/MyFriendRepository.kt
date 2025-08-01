package com.software.mywechat.core.data.repository

import com.software.mywechat.core.database.dao.FriendDao
import com.software.mywechat.core.database.dao.MyFriendDao
import com.software.mywechat.core.database.model.FriendEntity
import com.software.mywechat.core.database.model.MyFriendEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MyFriendRepository @Inject constructor(
    private val myFriendDao: MyFriendDao,
) {
    fun getAll(): Flow<List<MyFriendEntity>> = myFriendDao.getAll()

    suspend fun insert(data: MyFriendEntity) = myFriendDao.insert(data)

    suspend fun delete(data: MyFriendEntity) = myFriendDao.delete(data)


}