package com.demo.song_discovery.di

import com.demo.network.buildOkHttpClient
import com.demo.network.buildRetrofit
import com.demo.song_discovery.data.api.ItunesAPI
import com.demo.song_discovery.data.remote.ItunesRepositoryImpl
import com.demo.song_discovery.domain.ItunesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    fun provideNetworkClient(): ItunesAPI {
        val client = buildOkHttpClient()
        return buildRetrofit(client).create(ItunesAPI::class.java)
    }
}

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule{
    @Binds
    @ItunesRepo
    fun provideRepository(repository: ItunesRepositoryImpl) : ItunesRepository
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ItunesRepo