package com.example.modul4

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemTas(
    val nama: String,
    val gambar: Int,
    val harga: String,
    val deskripsi: String,
    val link: String
) : Parcelable


