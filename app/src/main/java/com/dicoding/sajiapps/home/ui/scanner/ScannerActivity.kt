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
import androidx.core.content.ContextCompat
import com.dicoding.sajiapps.databinding.ActivityScannerBinding
import com.dicoding.sajiapps.detail.DetailBahanActivity
import com.dicoding.sajiapps.detail.DetailResepActivity
import com.dicoding.sajiapps.utils.getImageUri

class ScannerActivity : AppCompatActivity() {

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
            showImage()
            showButton(true).apply {
                val intent = Intent(this@ScannerActivity, DetailResepActivity::class.java)
                startActivity(intent)
                finish()
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

    companion object {
        private const val REQUIRED_PERMISSION = Manifest.permission.CAMERA
    }
}