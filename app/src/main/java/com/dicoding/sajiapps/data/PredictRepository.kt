package com.dicoding.sajiapps.data

import com.dicoding.sajiapps.response.predict.PredictResponse
import com.dicoding.sajiapps.retrofit.ApiServicePredict
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class PredictRepository(private val apiService: ApiServicePredict) {
    suspend fun predictImage(file: MultipartBody.Part): PredictResponse? {
       return apiService.postPredict(file)
    }


    companion object {
        @Volatile
        private var instance: PredictRepository? = null

        fun getInstancePredict(
            apiServiceArticle: ApiServicePredict
        ) : PredictRepository =
            instance ?: synchronized(this) {
                instance ?: PredictRepository(apiServiceArticle)
            }.also { instance = it }
    }
}