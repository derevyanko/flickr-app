package com.example.flickrproject.di.data

import android.content.Context
import androidx.room.Room
import com.example.data.local.PhotoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun providesPhotoRoomDatabase(@ApplicationContext applicationContext: Context): PhotoDatabase =
        Room
            .databaseBuilder(applicationContext, PhotoDatabase::class.java, "photo_database.db")
            .build()

    @Provides
    @Singleton
    fun providesPhotosDao(appDatabase: PhotoDatabase) = appDatabase.getPhotosDao()
}