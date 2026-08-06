package com.example.borja

import kotlinx.serialization.Serializable

@Serializable
data object Home

@Serializable
data class Greeting(val userName: String)
