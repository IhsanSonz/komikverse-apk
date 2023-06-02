package com.example.komikverse.models

import androidx.annotation.DrawableRes
import java.io.Serializable

data class Comic(
    val _id: String,
    val title: String,
    val author: String,
    val thumb_url: String?,
    val desc: String
): Serializable