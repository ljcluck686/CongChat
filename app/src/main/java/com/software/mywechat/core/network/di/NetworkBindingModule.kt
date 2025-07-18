package com.software.mywechat.core.network.di

import com.software.mywechat.core.network.datasource.MyNetworkDataSource
import com.software.mywechat.core.network.datasource.MyRetrofitDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkBindingModule {

    @Binds
    @Singleton
    abstract fun bindMyNetworkDataSource(
        implementation: MyRetrofitDataSource
    ): MyNetworkDataSource
}