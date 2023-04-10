package com.example.amphibiansapp.uilayer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibiansapp.AmphibianApplication
import com.example.amphibiansapp.datalayer.AmphibianPhoto
import com.example.amphibiansapp.datalayer.AmphibianRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface AmphibianState {
    data class Success(val photos: List<AmphibianPhoto>) : AmphibianState
    object Loading : AmphibianState
    object Error : AmphibianState
}

class AmphibianViewModel(private val amphibianRepository: AmphibianRepository) : ViewModel() {

    /*private var _amphibianState = MutableStateFlow<AmphibianState>(AmphibianState.Loading)
    val amphibianState: StateFlow<AmphibianState> = _amphibianState.asStateFlow()*/

    var amphibianState: AmphibianState by mutableStateOf(AmphibianState.Loading)
        private set

    init {
        getAmphibians()
    }

    fun getAmphibians() {
        viewModelScope.launch {
            amphibianState = AmphibianState.Loading
            amphibianState = try {
                AmphibianState.Success(amphibianRepository.getAmphibianPhotos())
            } catch (e: IOException) {
                AmphibianState.Error
            }
        }
    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmphibianApplication)
                val amphibianRepository = application.container.amphibianRepository
                AmphibianViewModel(amphibianRepository = amphibianRepository)
            }
        }
    }
}