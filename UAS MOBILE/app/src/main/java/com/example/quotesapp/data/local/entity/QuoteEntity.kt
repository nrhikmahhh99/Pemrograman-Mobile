package com.example.quotesapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.quotesapp.domain.model.Quote

@Entity(tableName = "quotes")
data class QuoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val text: String,
    val author: String,
    val isFavorite: Boolean = false
)
fun QuoteEntity.toDomain(): Quote {
    return Quote(text = this.text, author = this.author, isFavorite = this.isFavorite)
}
fun Quote.toEntity(): QuoteEntity {
    return QuoteEntity(text = text, author = author, isFavorite = isFavorite)
}

