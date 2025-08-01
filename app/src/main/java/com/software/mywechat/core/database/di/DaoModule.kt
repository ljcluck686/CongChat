package com.software.mywechat.core.database.di

import com.software.mywechat.core.database.MyFriendDatabase
import com.software.mywechat.core.database.MyFriendListDatabase
import com.software.mywechat.core.database.dao.FriendDao
import com.software.mywechat.core.database.dao.MyFriendDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    fun providesFriendDao(
        database: MyFriendDatabase,
    ): FriendDao = database.friendDao()

    @Provides
    fun providesMyFriendDao(
        database: MyFriendListDatabase,
    ): MyFriendDao = database.myFriendDao()
}