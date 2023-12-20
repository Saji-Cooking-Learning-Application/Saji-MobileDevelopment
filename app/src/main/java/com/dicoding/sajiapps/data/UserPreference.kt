package com.dicoding.sajiapps.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.datastore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(name = "session")

class UserPreference private constructor(private val dataStore: DataStore<androidx.datastore.preferences.core.Preferences>) {

    suspend fun saveSession(user: UserModel) {
        dataStore.edit { preferences ->
            preferences[USERNAME_KEY] = user.username
//            preferences[EMAIL_KEY] = user.email
//            preferences[PHONENUMBER_KEY] = user.phoneNumber
//            preferences[FULLNAME_KEY] = user.fullName
            preferences[TOKEN_KEY] = user.token
            preferences[IS_LOGIN_KEY] = true
        }
    }

    fun getSession(): Flow<UserModel> {
        return dataStore.data.map { preferences ->
            UserModel(
//                preferences[EMAIL_KEY] ?: "",
//                preferences[PHONENUMBER_KEY] ?: "",
                preferences[USERNAME_KEY]?: "",
//                preferences[FULLNAME_KEY]?: "",
                preferences[TOKEN_KEY] ?: "",
                preferences[IS_LOGIN_KEY] ?: false
            )
        }
    }

    suspend fun logout() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreference? = null

        private val USERNAME_KEY = stringPreferencesKey("username")
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val IS_LOGIN_KEY = booleanPreferencesKey("isLogin")
//        private val EMAIL_KEY = stringPreferencesKey("email")
//        private val FULLNAME_KEY = stringPreferencesKey("name")
//        private val PHONENUMBER_KEY = stringPreferencesKey("hp")

        fun getInstance(dataStore: DataStore<androidx.datastore.preferences.core.Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}