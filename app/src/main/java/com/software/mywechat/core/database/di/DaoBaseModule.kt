package com.software.mywechat.core.database.di

import android.content.Context
import androidx.room.Room
import com.software.mywechat.MyAppState
import com.software.mywechat.core.database.MyFriendDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DaoBaseModule {
    @Provides
    fun providesMyDatabase(
        @ApplicationContext context: Context,
    ): MyFriendDatabase {
        if (MyAppState.myFriendDatabase == null) {
            val databaseName = "my_database_${MyAppState.userId}"
            MyAppState.myFriendDatabase = Room.databaseBuilder(
                context,
                MyFriendDatabase::class.java,
                databaseName,
            ).build()
        }
        return MyAppState.myFriendDatabase!!
    }
}