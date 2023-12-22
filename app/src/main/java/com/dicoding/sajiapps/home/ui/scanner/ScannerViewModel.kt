package com.dicoding.sajiapps.home.ui.scanner

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.sajiapps.data.PredictRepository
import com.dicoding.sajiapps.response.predict.PredictResponse
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.FileOutputStream
import java.io.InputStream
import java.io.File

class ScannerViewModel(private val predictRepository: PredictRepository) : ViewModel() {

    private val _predictionResult = MutableLiveData<PredictResponse>()
    val predictionResult: LiveData<PredictResponse> = _predictionResult

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun processImage(uri: Uri, context: Context) {
        viewModelScope.launch {
            val file = convertUriToFile(uri, context) // Implementasikan fungsi ini
            val requestFile = file.asRequestBody("image/jpg".toMediaTypeOrNull())
            val body = MultipartBody.Part.createFormData("photo", file.name, requestFile)
            predictImage(body) // Fungsi private yang ada
        }
    }

    private suspend fun predictImage(body: MultipartBody.Part) {
        val predictionResponse = predictRepository.predictImage(body)
        _predictionResult.value = predictionResponse!!
    }



    // Convert the Uri to a File object, implementing the necessary checks and copy operations
//    private fun uriToFile(uri: Uri, context: Context): File {
//        // Create a temporary file with a specific prefix and suffix (extension) in the cache directory
//        val tempFile = File.createTempFile("prefix_", ".suffix", context.cacheDir).apply {
//            // Delete the file when the VM exits
//            deleteOnExit()
//        }
//
//        // Get an InputStream from the Uri using the Content Resolver
//        context.contentResolver.openInputStream(uri)?.use { inputStream ->
//            // Create an OutputStream to write into the temporary file
//            FileOutputStream(tempFile).use { outputStream ->
//                // Copy the contents from the InputStream to the OutputStream
//                inputStream.copyTo(outputStream)
//            }
//        }
//
//        return tempFile
//    }
    private fun convertUriToFile(uri: Uri, context: Context): File {
        // Implementasi yang sama seperti di ScannerActivity
        val inputStream = context.contentResolver.openInputStream(uri)
        val file = File(context.cacheDir, "temp_image") // Gunakan timestamp atau nama unik jika perlu
        val outputStream = FileOutputStream(file)

        inputStream.use { input ->
            outputStream.use { output ->
                input?.copyTo(output)
            }
        }

        return file
    }
}
