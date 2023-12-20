package com.dicoding.sajiapps.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.dicoding.sajiapps.ViewModelFactory
import com.dicoding.sajiapps.databinding.ActivityRegisterBinding
import com.dicoding.sajiapps.login.LoginActivity
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    private val viewModel by viewModels<RegisterViewModels> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.daftarbtn.setOnClickListener {
            val fullname = binding.edNamaLengkap.text.toString().trim()
            val username = binding.edUsername.text.toString().trim()
            val password = binding.edPassword.text.toString().trim()
            val email = binding.edEmail.text.toString().trim()
            val numHp = binding.edPhone.text.toString().trim()
            lifecycleScope.launch {
                viewModel.register(username, password, fullname, email, numHp)
                AlertDialog.Builder(this@RegisterActivity).apply {
                    setTitle("Yeah!")
                    setMessage("Anda berhasil register. Sudah tidak sabar untuk bercerita yaa?? Login dulu yaa...")
                    setPositiveButton("Lanjut") { _, _ ->
                        val intent = Intent(context, LoginActivity::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                        finish()
                    }
                    create()
                    show()
                }
            }
        }
    }
}