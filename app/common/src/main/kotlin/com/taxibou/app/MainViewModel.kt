package com.taxibou.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.taxibou.ThemeRepository
import com.taxibou.UiMode
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.take

class MainViewModel(themeRepository: ThemeRepository) : ViewModel() {
    val uiState = themeRepository.uiModeFlow.take(1).map { MainUiState.Success(it) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, MainUiState.SplashScreen)

    companion object {
        val Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST") return MainViewModel(InMemoryThemeRepository()) as T
            }
        }
    }
}


class InMemoryThemeRepository : ThemeRepository {
    private val channel: Channel<UiMode> = Channel(Channel.CONFLATED)
    override val uiModeFlow: Flow<UiMode> = channel.receiveAsFlow()

    init {
        channel.trySend(UiMode.System)
    }

    override suspend fun updateUiMode(mode: UiMode) {
        channel.send(mode)
    }

}

sealed interface MainUiState {
    data object SplashScreen : MainUiState
    data class Success(val uiMode: UiMode) : MainUiState

    val isSplashScreen: Boolean get() = this is SplashScreen
}