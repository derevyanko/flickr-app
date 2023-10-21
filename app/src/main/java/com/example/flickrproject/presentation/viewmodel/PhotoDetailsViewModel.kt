package com.example.flickrproject.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.base.State
import com.example.domain.models.details.PhotoDetails
import com.example.domain.usecases.GetPhotoDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class PhotoDetailsViewModel @Inject constructor(
    private val getPhotoDetailsUseCase: GetPhotoDetailsUseCase,
    coroutineContext: CoroutineContext
): ViewModel() {

    private val scope = CoroutineScope(coroutineContext)

    private val _photoDetails = MutableLiveData<State<PhotoDetails>>()
    val photoDetails = _photoDetails

    fun getPhotoDetails(photoId: String) = scope.launch {
        getPhotoDetailsUseCase.invoke(photoId).collect {
            _photoDetails.postValue(it)
        }
    }
}