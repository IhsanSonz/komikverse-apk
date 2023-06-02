package com.example.komikverse.models

import java.io.Serializable

data class Chapter (
    val _id: String,
    val title: String,
    val index: Int,
) : Serializable