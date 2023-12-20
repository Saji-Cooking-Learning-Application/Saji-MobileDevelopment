package com.dicoding.sajiapps

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.sajiapps.data.UserRepository
import com.dicoding.sajiapps.di.Injection
import com.dicoding.sajiapps.home.ui.home.HomeViewModel
import com.dicoding.sajiapps.login.LoginViewModels
import com.dicoding.sajiapps.register.RegisterViewModels

class ViewModelFactory(private val repository: UserRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
//            modelClass.isAssignableFrom(Main::class.java) -> {
//                MainViewModel(repository) as T
//            }
            modelClass.isAssignableFrom(LoginViewModels::class.java) -> {
                LoginViewModels(repository) as T
            }
            modelClass.isAssignableFrom(RegisterViewModels::class.java) -> {
                RegisterViewModels(repository) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(Injection.provideRepository(context))
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}