package com.example.quotesapp.domain.model

data class Quote(
    val text: String,
    val author: String,
    val isFavorite: Boolean = false
)
