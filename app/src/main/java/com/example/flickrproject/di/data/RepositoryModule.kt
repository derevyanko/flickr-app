package com.example.flickrproject.di.data

import com.example.data.local.dao.PhotosDao
import com.example.data.local.dataSource.FlickrLocalDataSource
import com.example.data.local.dataSource.FlickrLocalDataSourceImpl
import com.example.data.remote.api.FlickrApiService
import com.example.data.remote.base.ErrorHandlerImpl
import com.example.data.remote.dataSource.FlickrRemoteDataSource
import com.example.data.remote.dataSource.FlickrRemoteDataSourceImpl
import com.example.data.repository.FlickrRepositoryImpl
import com.example.domain.base.ErrorHandler
import com.example.domain.repository.FlickrRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providesFlickrRepository(
        api: FlickrRemoteDataSource,
        database: FlickrLocalDataSource,
        errorHandler: ErrorHandler
    ): FlickrRepository = FlickrRepositoryImpl(
        api = api,
        database = database,
        errorHandler = errorHandler
    )

    @Provides
    @Singleton
    fun providesFlickrRemoteRepository(flickrApiService: FlickrApiService): FlickrRemoteDataSource =
        FlickrRemoteDataSourceImpl(apiService = flickrApiService)

    @Provides
    @Singleton
    fun providesFlickrLocalRepository(recentPhotosDao: PhotosDao): FlickrLocalDataSource =
        FlickrLocalDataSourceImpl(photosDao = recentPhotosDao)

    @Provides
    @Singleton
    fun providesErrorHandler(): ErrorHandler = ErrorHandlerImpl()
}