package com.example.modul5.data.api

import kotlinx.serialization.Serializable

@Serializable
data class TasDto(
    val id: Int,
    val nama: String,
    val harga: String,
    val gambar: String,
    val shopeeLink: String,
    val deskripsi: String
)
