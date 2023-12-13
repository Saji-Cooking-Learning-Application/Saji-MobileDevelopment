package com.dicoding.sajiapps.response

data class LoginResponse(
	val code: Int? = null,
	val userId: Int? = null,
	val message: String? = null,
	val accessToken: String? = null,
	val status: String? = null,
	val refreshToken: String? = null
)

