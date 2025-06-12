package com.example.modul5.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.modul5.data.local.TasEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TasDao {
    @Query("SELECT * FROM tas")
    fun getAllTas(): Flow<List<TasEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tas: List<TasEntity>)

    @Query("UPDATE tas SET isFavorite = :isFav WHERE id = :id")
    suspend fun updateFavorite(id: Int, isFav: Boolean)
}
