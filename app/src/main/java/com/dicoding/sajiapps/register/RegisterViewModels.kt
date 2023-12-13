package com.dicoding.sajiapps.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.sajiapps.data.UserRepository
import com.google.gson.Gson

class RegisterViewModels(private val repository: UserRepository): ViewModel() {
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> get() = _msg

    suspend fun register(username: String, password: String, nama: String, email: String, hp: String){
        try {
            _loading.value = false
            val registerResponse = repository.register(username, password, nama, email, hp)
            val messageRegis = registerResponse.message
            _msg.value = messageRegis!!
        } catch (e: Exception){
            _loading.value = false
            _msg.value = "Terjadi kesalahan saat melakukan Register"
        }
    }
}