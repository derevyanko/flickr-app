package com.example.flickrproject.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.base.State
import com.example.domain.models.recent.RecentPhotos
import com.example.domain.usecases.GetRecentPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class RecentPhotosViewModel @Inject constructor(
    private val getRecentPhotosUseCase: GetRecentPhotosUseCase,
    coroutineContext: CoroutineContext
): ViewModel() {

    private val scope = CoroutineScope(coroutineContext)

    private val itemsPerPage = 9

    private val _recentPhotos = MutableLiveData<State<RecentPhotos>>()
    val recentPhotos = _recentPhotos

    fun getPhotosList() = scope.launch {
        getRecentPhotosUseCase.invoke(itemsPerPage = itemsPerPage).collect {
            _recentPhotos.postValue(it)
        }
    }
}