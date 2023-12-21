package com.dicoding.sajiapps.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.dicoding.sajiapps.ViewModelFactory
import com.dicoding.sajiapps.databinding.ActivityDetailresepBinding
import com.dicoding.sajiapps.response.Data
import com.dicoding.sajiapps.response.DetailResepResponse
import kotlinx.coroutines.launch

class DetailResepActivity : AppCompatActivity() {
    private val viewModel by viewModels<DetailViewModels> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityDetailresepBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailresepBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val id = intent.getIntExtra(EXTRA_ID, 0)
        lifecycleScope.launch {
            viewModel.setDetailResep(id)
        }
        viewModel.detailResep.observe(this) { detailResep ->
            detailResep?.let {
                displayDetail(it)
            }
        }
    }

    private fun displayDetail(detailResep: Data) {
        binding.apply {
            Glide.with(binding.root)
                .load(intent.getStringExtra(IMG))
                .into(binding.ivDetailResep)
            tvJudulResep.text = intent.getStringExtra(EXTRA_MENU)
            // Tambahkan kode untuk menampilkan data lainnya ke dalam UI
        }
    }

    companion object {
        const val EXTRA_ID = "EXTRA_ID"
        const val EXTRA_MENU = "EXTRA_MENU"
        const val IMG = "DETAIL_IMG"
        const val DESKRIPSI = "DEKSRIPSI"
    }
}