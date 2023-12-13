package com.dicoding.sajiapps.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.dicoding.sajiapps.ViewModelFactory
import com.dicoding.sajiapps.data.UserModel
import com.dicoding.sajiapps.databinding.ActivityLoginBinding
import com.dicoding.sajiapps.home.HomeActivity
import com.dicoding.sajiapps.home.ui.home.HomeFragment
import com.dicoding.sajiapps.register.RegisterActivity
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private val viewModel by viewModels<LoginViewModels> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.loginBtn.setOnClickListener {
            val username = binding.edUsername.text.toString().trim()
            val password = binding.edPassword.text.toString().trim()
            lifecycleScope.launch {
                val resultLogin = viewModel.validateAndLogin(username, password)
                val message = resultLogin?.message
                if (message != "error"){
                    when(resultLogin != null){
                        true -> {
                            AlertDialog.Builder(this@LoginActivity).apply {
                                setTitle("Yeah!")
                                setMessage("Anda berhasil Login. Selamat Datang :D.")
                                setPositiveButton("Lanjut") { _, _ ->
                                    val token = resultLogin.accessToken
                                    viewModel.saveSession(UserModel(username, token.toString()))
                                    val intent = Intent(context, HomeActivity::class.java)
                                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                    startActivity(intent)
                                    finish()
                                }
                                create()
                                show()
                            }
                        }
                        else -> {
                            AlertDialog.Builder(this@LoginActivity).apply {
                                setTitle("Failed!")
                                setMessage("Gagal Login, Coba Cek Username atau Password Anda")
                                setPositiveButton("Lanjut") { _, _ ->
                                }
                                create()
                                show()
                            }
                        }
                    }
                }
            }
        }
        binding.daftarSekarang.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        supportActionBar?.hide()
    }
}