package com.example.domain.usecases

import com.example.domain.repository.FlickrRepository

class GetPhotoDetailsUseCase(
    private val repository: FlickrRepository
) {

    suspend operator fun invoke(photoId: String) = repository.getPhotoById(photoId)
}