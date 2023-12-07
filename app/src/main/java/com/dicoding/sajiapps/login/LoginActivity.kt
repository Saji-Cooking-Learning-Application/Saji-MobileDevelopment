package com.dicoding.sajiapps.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.sajiapps.R
import com.dicoding.sajiapps.databinding.ActivityLoginBinding
import com.dicoding.sajiapps.home.HomeActivity
import com.dicoding.sajiapps.home.ui.home.HomeFragment

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.loginBtn.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        supportActionBar?.hide()
    }
}