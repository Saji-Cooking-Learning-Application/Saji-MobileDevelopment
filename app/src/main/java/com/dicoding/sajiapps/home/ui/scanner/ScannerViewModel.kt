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
import okhttp3.MultipartBody
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

    fun predictImage(imageUri: Uri, context: Context) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // Convert the Uri to a File object
                val file = uriToFile(imageUri, context)

                val predictionResponse = predictRepository.predictImage(file)
                _isLoading.value = false
                if (predictionResponse != null) {
                    _predictionResult.value = predictionResponse!!
                } else {
                    _errorMessage.value = "Failed to get the prediction results."
                }
            } catch (e: Exception) {
                _isLoading.value = false
                _errorMessage.value = e.localizedMessage ?: "An unexpected error occurred"
            }
        }
    }

    // Convert the Uri to a File object, implementing the necessary checks and copy operations
    private fun uriToFile(uri: Uri, context: Context): File {
        // Create a temporary file with a specific prefix and suffix (extension) in the cache directory
        val tempFile = File.createTempFile("prefix_", ".suffix", context.cacheDir).apply {
            // Delete the file when the VM exits
            deleteOnExit()
        }

        // Get an InputStream from the Uri using the Content Resolver
        context.contentResolver.openInputStream(uri)?.use { inputStream ->
            // Create an OutputStream to write into the temporary file
            FileOutputStream(tempFile).use { outputStream ->
                // Copy the contents from the InputStream to the OutputStream
                inputStream.copyTo(outputStream)
            }
        }

        return tempFile
    }
}
