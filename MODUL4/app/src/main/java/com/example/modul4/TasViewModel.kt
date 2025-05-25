package com.example.modul4

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TasViewModel : ViewModel() {

    private val _tasList = MutableStateFlow<List<ItemTas>>(emptyList())
    val tasList: StateFlow<List<ItemTas>> = _tasList

    private val _selectedTas = MutableStateFlow<ItemTas?>(null)
    val selectedTas: StateFlow<ItemTas?> = _selectedTas

    private val _navigateToDetail = MutableSharedFlow<Unit>()
    val navigateToDetail: SharedFlow<Unit> = _navigateToDetail

    fun selectTas(item: ItemTas?) {
        _selectedTas.value = item
        Log.d("TasViewModel", "Item dipilih: ${item?.nama}")
        if (item != null) {
            viewModelScope.launch {
                _navigateToDetail.emit(Unit)
            }
        }
    }

    fun loadData(data: List<ItemTas>) {
        _tasList.value = data
        Log.d("TasViewModel", "Data item dimuat: ${data.size} item")
    }

    fun clearSelectedTas() {
        _selectedTas.value = null
    }
}
