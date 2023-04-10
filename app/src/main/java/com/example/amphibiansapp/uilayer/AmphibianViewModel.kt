package com.example.amphibiansapp.uilayer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amphibiansapp.datalayer.AmphibianPhoto
import com.example.amphibiansapp.networklayer.AmphibianApi
import com.example.amphibiansapp.networklayer.AmphibianApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface AmphibianState {
    data class Success(val photos: List<AmphibianPhoto>) : AmphibianState
    object Loading : AmphibianState
    object Error : AmphibianState
}

class AmphibianViewModel : ViewModel() {

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
                AmphibianState.Success(AmphibianApi.retrofitService.getAmphibians())
            } catch (e: IOException) {
                AmphibianState.Error
            }
        }
    }

}