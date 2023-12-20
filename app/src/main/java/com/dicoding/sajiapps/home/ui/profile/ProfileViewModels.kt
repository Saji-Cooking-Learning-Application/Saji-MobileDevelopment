package com.dicoding.sajiapps.home.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.sajiapps.data.UserRepository
import kotlinx.coroutines.launch

class ProfileViewModels(private val repository: UserRepository): ViewModel() {
    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }
}