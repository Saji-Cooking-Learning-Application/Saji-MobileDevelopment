package com.dicoding.sajiapps.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.sajiapps.R
import com.dicoding.sajiapps.databinding.ActivityDetailBahanBinding

class DetailBahanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBahanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBahanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

    }
}