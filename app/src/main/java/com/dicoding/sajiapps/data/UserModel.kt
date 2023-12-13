package com.dicoding.sajiapps.data

data class UserModel(
    val email: String,
    val token: String,
    val isLogin: Boolean = false
)