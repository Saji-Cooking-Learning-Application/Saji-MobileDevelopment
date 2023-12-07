package com.dicoding.sajiapps.home.ui.scanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.sajiapps.R

class ScannerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner)
        supportActionBar?.hide()
    }
}