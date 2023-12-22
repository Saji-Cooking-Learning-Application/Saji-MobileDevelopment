package com.dicoding.sajiapps.home.ui.scanner

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.dicoding.sajiapps.ViewModelFactory
import com.dicoding.sajiapps.databinding.ActivityScannerBinding
import com.dicoding.sajiapps.detail.DetailBahanActivity
import com.dicoding.sajiapps.detail.DetailResepActivity
import com.dicoding.sajiapps.login.LoginViewModels
import com.dicoding.sajiapps.utils.getImageUri
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream

class ScannerActivity : AppCompatActivity() {
    private val viewModel by viewModels<ScannerViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private lateinit var binding: ActivityScannerBinding
    private var currentImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScannerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        if (!allPermissionsGranted()) {
            requestPermissionLauncher.launch(REQUIRED_PERMISSION)
        }

        viewModel.predictionResult.observe(this, { predictionResponse ->
//            // Handle the prediction result, e.g., update the UI or navigate based on the result
//            // For example, if you want to pass data to DetailResepActivity:
//            val intent = Intent(this, DetailResepActivity::class.java).apply {
//                // pass data with intent.putExtra()
//            }
//            startActivity(intent)
//            finish()
        })

        viewModel.isLoading.observe(this, { isLoading ->
            // Show or hide loading indicator
            // e.g., binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })

        viewModel.errorMessage.observe(this, { errorMessage ->
            // Show error message
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        })
        binding.cameraDeteksiMakanan.setOnClickListener { startCamera() }
        binding.cameraDeteksiBahan.setOnClickListener { startCameraBahan() }
    }

    private fun startCamera() {
        currentImageUri = getImageUri(this)
        launcherIntentCamera.launch(currentImageUri)
    }
    private fun startCameraBahan() {
        currentImageUri = getImageUri(this)
        launcherIntentCameraBahan.launch(currentImageUri)
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess) {
            currentImageUri?.let { uri ->
                viewModel.processImage(uri, this@ScannerActivity)
            }
        }
    }


    private val launcherIntentCameraBahan = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess) {
            showImage()
            showButton(true).apply {
                val intent = Intent(this@ScannerActivity, DetailBahanActivity::class.java)
                startActivity(intent)
                finish()

            }
        }
    }

    private fun showImage() {
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.imageView2.setImageURI(it)
        }
    }
    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(this, "Permission request granted", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Permission request denied", Toast.LENGTH_LONG).show()
            }
        }
    private fun allPermissionsGranted() =
        ContextCompat.checkSelfPermission(
            this,
            REQUIRED_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED
    private fun showButton(isShow: Boolean) {
        binding.galeriBtn.visibility = if (isShow) View.VISIBLE else View.GONE
    }
    private fun convertUriToFile(uri: Uri): File {
        val inputStream = contentResolver.openInputStream(uri)
        val file = File(cacheDir, "temp_image") // You can use a timestamp or other naming convention
        val outputStream = FileOutputStream(file)

        inputStream.use { input ->
            outputStream.use { output ->
                input?.copyTo(output)
            }
        }

        return file
    }

    companion object {
        private const val REQUIRED_PERMISSION = Manifest.permission.CAMERA
    }
}