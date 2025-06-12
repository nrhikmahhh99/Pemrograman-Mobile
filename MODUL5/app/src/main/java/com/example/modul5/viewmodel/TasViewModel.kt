package com.example.modul5.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modul5.domain.model.Tas
import com.example.modul5.data.repository.TasRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TasViewModel(
    private val repository: TasRepository
) : ViewModel() {
    val tasList: StateFlow<List<Tas>> =
        repository.tasList
            .onEach { println("ViewModel: Jumlah data dari Room = ${it.size}") }
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    private val _selectedTas = MutableStateFlow<Tas?>(null)
    val selectedTas: StateFlow<Tas?> = _selectedTas
    private val _navigateToDetail = MutableSharedFlow<Unit>()
    val navigateToDetail: SharedFlow<Unit> = _navigateToDetail

    init {
        refreshFromApi()
    }

    fun refreshFromApi() {
        viewModelScope.launch {
            try {
                repository.refreshFromApi()
            } catch (e: Exception) {
            }
        }
    }

    fun toggleFavorite(tas: Tas) {
        viewModelScope.launch {
            repository.toggleFavorite(tas.id, tas.isFavorite)
        }
    }

    fun selectTas(item: Tas?) {
        _selectedTas.value = item
        if (item != null) {
            viewModelScope.launch {
                _navigateToDetail.emit(Unit)
            }
        }
    }

    fun clearSelectedTas() {
        _selectedTas.value = null
    }
}
