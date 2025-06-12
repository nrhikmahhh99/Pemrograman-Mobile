package com.example.modul5.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tas")
data class TasEntity(
    @PrimaryKey val id: Int,
    val nama: String,
    val harga: String,
    val gambar: String,
    val shopeeLink: String,
    val deskripsi: String,
    val isFavorite: Boolean = false
)
