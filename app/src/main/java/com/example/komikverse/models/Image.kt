package com.example.komikverse.models

import java.io.Serializable

data class Image(
    val _id: String,
    val index: Int,
    val image_url: String,
): Serializable
