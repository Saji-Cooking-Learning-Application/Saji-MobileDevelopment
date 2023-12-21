package com.dicoding.sajiapps.data

import com.dicoding.sajiapps.response.predict.PredictResponse
import com.dicoding.sajiapps.retrofit.ApiServicePredict
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class PredictRepository(private val apiService: ApiServicePredict) {
    suspend fun predictImage(file: File): PredictResponse? {
        // Create a RequestBody instance from the file
        val requestFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())

        // MultipartBody.Part is used to send also the actual file name
        val body = MultipartBody.Part.createFormData("file", file.name, requestFile)

        // Make the API call
        val response = apiService.postPredict(body)

        if (response.isSuccessful) {
            // Return the body of the response
            return response.body()
        } else {
            // Throw an exception or handle errors as needed
            throw Exception(response.errorBody()?.string() ?: "An error occurred during the API call")
        }
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