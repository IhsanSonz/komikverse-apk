package com.example.komikverse.models

import java.io.Serializable

data class PrevNext(
    val prev: Chapter?,
    val next: Chapter?,
) : Serializable
