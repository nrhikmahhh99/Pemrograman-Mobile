package com.example.modul5.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
@Parcelize
data class Tas(
    val id: Int,
    val nama: String,
    val harga: String,
    val gambar: String,
    val shopeeLink: String,
    val deskripsi: String,
    @Transient val isFavorite: Boolean = false
) : Parcelable
