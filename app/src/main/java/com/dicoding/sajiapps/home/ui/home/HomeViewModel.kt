package com.dicoding.sajiapps.home.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.sajiapps.data.UserRepository
import com.dicoding.sajiapps.response.DataItem
import com.dicoding.sajiapps.response.ResepResponse
import com.google.gson.Gson
import kotlinx.coroutines.flow.first
import retrofit2.HttpException

class HomeViewModel(private val repository: UserRepository) : ViewModel() {
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _listFood = MutableLiveData<List<DataItem>>()
    val listFood: LiveData<List<DataItem>> get() = _listFood

    private val _cekMessage = MutableLiveData<String>()
    val  cekMessage: LiveData<String> get() = _cekMessage

    suspend fun getAllFood(){
        val tokenStory = repository.getSession().first().token
        val story = repository.getResep("Bearer $tokenStory")
        val message = story.message
        try {
            _loading.value = false
            //get success message
            _listFood.value = story.data
            _cekMessage.value = message!!
        } catch (e: HttpException) {
            //get error message
            _loading.value = false
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ResepResponse::class.java)
            val errorMessage = errorBody.message
            errorMessage!!
        }
    }
}