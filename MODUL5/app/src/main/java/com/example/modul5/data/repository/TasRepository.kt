package com.example.modul5.data.repository

import android.util.Log
import com.example.modul5.domain.model.Tas
import com.example.modul5.data.api.TasApiService
import com.example.modul5.data.local.TasDao
import com.example.modul5.data.mapper.toDomain
import com.example.modul5.data.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class TasRepository(
    private val api: TasApiService,
    private val dao: TasDao
) {
    val tasList: Flow<List<Tas>> = dao.getAllTas().map { list ->
        list.map { it.toDomain() }
    }

    suspend fun refreshFromApi() {
        val response = api.getTas()
        val existing = dao.getAllTas().firstOrNull()
        if (existing.isNullOrEmpty()) {
            dao.insertAll(response.map { it.toEntity() })
        }
    }

    suspend fun toggleFavorite(id: Int, current: Boolean) {
        dao.updateFavorite(id, !current)
    }
}
