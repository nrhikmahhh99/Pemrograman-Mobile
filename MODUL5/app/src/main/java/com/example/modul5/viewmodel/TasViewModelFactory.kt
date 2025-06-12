package com.example.modul5.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.modul5.data.api.ApiClient
import com.example.modul5.data.local.TasDatabase
import com.example.modul5.data.repository.TasRepository

class TasViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TasViewModel::class.java)) {
            val db = TasDatabase.getDatabase(context)
            val dao = db.tasDao()
            val api = ApiClient.retrofit
            val repository = TasRepository(api, dao)
            return TasViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
