package com.example.data.repository

import com.example.data.local.dataSource.FlickrLocalDataSource
import com.example.data.mappers.PhotoDetailsMapper
import com.example.data.mappers.RecentPhotosMapper
import com.example.data.remote.dataSource.FlickrRemoteDataSource
import com.example.domain.base.ErrorEntity
import com.example.domain.base.ErrorHandler
import com.example.domain.base.State
import com.example.domain.repository.FlickrRepository
import com.example.data.remote.base.NetworkState
import kotlinx.coroutines.flow.flow
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

class FlickrRepositoryImpl @Inject constructor(
    private val api: FlickrRemoteDataSource,
    private val database: FlickrLocalDataSource,
    private val errorHandler: ErrorHandler
): FlickrRepository {

    private val currentDate: String
        get() = SimpleDateFormat("dd/MM/yyyy").let { formatter ->
            val date = Date()
            return@let formatter.format(date)
        }

    override suspend fun getRecentPhotos(itemsPerPage: Int) = flow {
        emit(State.Loading)

        val dbResult = database.getRecentPhotos(currentDate)
        RecentPhotosMapper.fromDbEntityToDomain(dbResult).also {
            emit(State.Success(data = it))
        }

        when (val apiResult = api.getRecentPhotos(itemsPerPage)) {
            is NetworkState.Success -> {
                val photosDto = apiResult.data?.photos ?: return@flow emit(State.Failure(error = ErrorEntity.Unknown))
                val recentPhotos = RecentPhotosMapper.fromDtoToDomain(photosDto)
                emit(State.Success(data = recentPhotos))

                val recentPhotoDbEntities = RecentPhotosMapper.fromDtoToDbEntity(photosDto, currentDate)
                database.updateRecentPhotos(recentPhotoDbEntities)
            }
            is NetworkState.Failure -> if (dbResult.isEmpty()) {
                emit(State.Failure(error = errorHandler.getError(apiResult.statusCode)))
            }
            else -> {}
        }
    }

    override suspend fun getPhotoById(photoId: String) = flow {
        emit(State.Loading)

        val dbResult = database.getPhotoDetails(photoId)
        dbResult?.let {
            emit(State.Success(data = PhotoDetailsMapper.fromDbEntityToDomain(it)))
        }

        when (val apiResult = api.getInfoById(photoId)) {
            is NetworkState.Success -> {
                val photoDetailsDto = apiResult.data?.photo ?: return@flow
                val photoDetailsModel = PhotoDetailsMapper.fromDtoToDomain(photoDetailsDto)
                emit(State.Success(data = photoDetailsModel))

                val photoDetailsDbEntity = PhotoDetailsMapper.fromDtoToDbEntity(photoDetailsDto)
                database.updatePhotoDetails(photoDetailsDbEntity)
            }
            is NetworkState.Failure -> if (dbResult == null) {
                emit(State.Failure(error = errorHandler.getError(apiResult.statusCode)))
            }
            else -> {}
        }
    }
}