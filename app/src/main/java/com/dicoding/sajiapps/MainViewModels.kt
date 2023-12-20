package com.dicoding.sajiapps

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.sajiapps.data.UserModel
import com.dicoding.sajiapps.data.UserRepository

class MainViewModels(private val repository: UserRepository): ViewModel() {
    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }
}