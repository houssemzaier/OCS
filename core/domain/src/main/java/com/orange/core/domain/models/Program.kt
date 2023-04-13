package com.orange.core.domain.models

data class Program(
    val id: String,
    val title: String,
    val subtitle: String,
    val pitch: String,
    val thumbnailUrl: String,
    val imageUrl: String,
    val detailLink: String,
)
