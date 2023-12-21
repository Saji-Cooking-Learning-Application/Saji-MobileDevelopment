package com.dicoding.sajiapps.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.sajiapps.data.UserRepository
import com.dicoding.sajiapps.response.Data
import com.dicoding.sajiapps.response.DetailResepResponse
import com.google.gson.Gson
import kotlinx.coroutines.flow.first
import retrofit2.HttpException

class DetailViewModels(private val repository: UserRepository): ViewModel() {
    private val _detailResep = MutableLiveData<Data>()
    val  detailResep: LiveData<Data> get() = _detailResep
    private val _cekMessage = MutableLiveData<String>()
    val  cekMessage: LiveData<String> get() = _cekMessage

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    suspend fun setDetailResep(id: Int): Data? {
        val token = repository.getSession().first().token
        try {
            _loading.value = true
            val detailResponse = repository.getDetailResep("Bearer $token", id)
            _detailResep.value = detailResponse!!
            _cekMessage.value = "Success" // Pesan sukses dapat disesuaikan dengan kebutuhan Anda
        } catch (e: HttpException) {
            _loading.value = false
            val errorBody = e.response()?.errorBody()?.string()
            val errorMessage = Gson().fromJson(errorBody, DetailResepResponse::class.java)?.message
            _cekMessage.value = errorMessage ?: "Unknown Error"
        } catch (e: Exception) {
            _loading.value = false
            _cekMessage.value = "Error: ${e.message}"
        }
        return repository.getDetailResep(token, id)
    }
}