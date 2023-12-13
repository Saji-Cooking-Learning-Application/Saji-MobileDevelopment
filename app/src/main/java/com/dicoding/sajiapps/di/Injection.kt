package com.dicoding.sajiapps.di

import android.content.Context
import com.dicoding.sajiapps.data.UserPreference
import com.dicoding.sajiapps.data.UserRepository
import com.dicoding.sajiapps.data.datastore
import com.dicoding.sajiapps.retrofit.ApiConfig
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.datastore)
        val user = runBlocking { pref.getSession().first() }
        val apiService = ApiConfig.getApiService(user.token)
        return UserRepository.getInstance(pref, apiService)
    }
}