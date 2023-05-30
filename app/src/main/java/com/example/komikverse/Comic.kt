package com.example.komikverse

import androidx.annotation.DrawableRes

data class Comic(
    val id: String,
    val title: String,
    val author: String,
    val thumbUrl: String?,
    val desc: String
)