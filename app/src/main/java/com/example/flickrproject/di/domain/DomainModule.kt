package com.example.flickrproject.di.domain

import com.example.domain.repository.FlickrRepository
import com.example.domain.usecases.GetPhotoDetailsUseCase
import com.example.domain.usecases.GetRecentPhotosUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun providesGetRecentPhotosUseCase(
        repository: FlickrRepository
    ): GetRecentPhotosUseCase =
        GetRecentPhotosUseCase(repository)

    @Provides
    fun providesGetPhotoDetailsUseCase(
        repository: FlickrRepository
    ): GetPhotoDetailsUseCase =
        GetPhotoDetailsUseCase(repository)

    @Provides
    @Singleton
    fun providesCoroutineContext(): CoroutineContext = Job() + Dispatchers.IO
}