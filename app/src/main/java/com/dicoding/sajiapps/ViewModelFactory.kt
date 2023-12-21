package com.dicoding.sajiapps

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.sajiapps.data.PredictRepository
import com.dicoding.sajiapps.data.UserRepository
import com.dicoding.sajiapps.detail.DetailViewModels
import com.dicoding.sajiapps.di.Injection
import com.dicoding.sajiapps.home.ui.home.HomeViewModel
import com.dicoding.sajiapps.home.ui.kulkas.KulkasViewModel
import com.dicoding.sajiapps.home.ui.profile.ProfileViewModels
import com.dicoding.sajiapps.home.ui.scanner.ScannerViewModel
import com.dicoding.sajiapps.login.LoginViewModels
import com.dicoding.sajiapps.register.RegisterViewModels

class ViewModelFactory(private val repository: UserRepository, private val predictRepository: PredictRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModels::class.java) -> {
                MainViewModels(repository) as T
            }
            modelClass.isAssignableFrom(LoginViewModels::class.java) -> {
                LoginViewModels(repository) as T
            }
            modelClass.isAssignableFrom(RegisterViewModels::class.java) -> {
                RegisterViewModels(repository) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(repository) as T
            }modelClass.isAssignableFrom(ProfileViewModels::class.java) -> {
                ProfileViewModels(repository) as T
            }modelClass.isAssignableFrom(DetailViewModels::class.java) -> {
                DetailViewModels(repository) as T
            }modelClass.isAssignableFrom(KulkasViewModel::class.java) -> {
                KulkasViewModel(repository) as T
            }modelClass.isAssignableFrom(ScannerViewModel::class.java) -> {
                ScannerViewModel(predictRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: ViewModelFactory(
                    Injection.provideRepository(context),
                    Injection.providePredictRepository(context) // Make sure you have this method in Injection object
                ).also { INSTANCE = it }
            }
        }
    }
}