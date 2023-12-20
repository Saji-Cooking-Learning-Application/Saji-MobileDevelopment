package com.dicoding.sajiapps.data

import com.dicoding.sajiapps.response.LoginResponse
import com.dicoding.sajiapps.response.RegisterResponse
import com.dicoding.sajiapps.response.ResepResponse
import com.dicoding.sajiapps.retrofit.ApiService
import kotlinx.coroutines.flow.Flow

class UserRepository private constructor(
    private val userPreference: UserPreference,
    private val apiService: ApiService,
) {

    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }


    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun getResep(token: String): ResepResponse{
        return apiService.getResep(token)
    }
    suspend fun logout() {
        userPreference.logout()
    }

    suspend fun login(username: String, password: String): LoginResponse{
        return apiService.login(username, password)
    }
    suspend fun register(username: String, password: String, nama: String, email: String, hp: String): RegisterResponse{
        return apiService.register(username, password, nama, email, hp)
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            userPreference: UserPreference,
            apiService: ApiService
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userPreference, apiService)
            }.also { instance = it }
    }
}