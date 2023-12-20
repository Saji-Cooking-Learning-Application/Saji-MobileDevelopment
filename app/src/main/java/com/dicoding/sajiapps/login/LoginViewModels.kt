package com.dicoding.sajiapps.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.sajiapps.data.UserModel
import com.dicoding.sajiapps.data.UserRepository
import com.dicoding.sajiapps.response.LoginResponse
import kotlinx.coroutines.launch

class LoginViewModels(private val repository: UserRepository) : ViewModel() {
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> get() = _msg

    suspend fun validateAndLogin(username: String, password: String): LoginResponse? {
        // Melakukan validasi username dan password
        if (isCredentialsValid(username, password)) {
            try {
                _loading.value = true

                // Lakukan login jika kredensial valid
                val responseLogin = repository.login(username, password)

                _loading.value = false
                return responseLogin
            } catch (e: Exception) {
                _loading.value = false
                _msg.value = "Terjadi kesalahan saat melakukan login"
            }
        } else {
            _msg.value = "Username atau password tidak valid"
        }
        return null
    }

    private fun isCredentialsValid(username: String, password: String): Boolean {
        // Lakukan validasi sesuai dengan kriteria yang Anda inginkan
        // Misalnya, pastikan panjang username minimal 6 karakter, password memiliki kombinasi karakter tertentu, dll.
        return username.length >= 1 && password.length >= 8
    }

    fun saveSession(user: UserModel){
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }
}

