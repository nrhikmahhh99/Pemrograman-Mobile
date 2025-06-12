package com.example.modul5.data.mapper

import com.example.modul5.data.api.TasDto
import com.example.modul5.data.local.TasEntity
import com.example.modul5.domain.model.Tas

fun TasDto.toEntity(): TasEntity {
    return TasEntity(
        id = this.id,
        nama = this.nama,
        harga = this.harga,
        gambar = this.gambar,
        shopeeLink = this.shopeeLink,
        deskripsi = this.deskripsi,
        isFavorite = false // default
    )
}

fun TasEntity.toDomain(): Tas {
    return Tas(
        id = this.id,
        nama = this.nama,
        harga = this.harga,
        gambar = this.gambar,
        shopeeLink = this.shopeeLink,
        deskripsi = this.deskripsi,
        isFavorite = this.isFavorite
    )
}