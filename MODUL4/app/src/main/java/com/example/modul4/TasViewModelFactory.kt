package com.example.modul4

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TasViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TasViewModel::class.java)) {
            return TasViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
