package com.dicoding.sajiapps.detail

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.dicoding.sajiapps.R.*
import com.dicoding.sajiapps.ViewModelFactory
import com.dicoding.sajiapps.databinding.ActivityDetailresepBinding
import com.dicoding.sajiapps.databinding.ItemResepBinding
import com.dicoding.sajiapps.response.Data
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
        val listView = findViewById<ListView>(com.dicoding.sajiapps.R.id.list_bahan)

        // Buat ArrayList untuk menyimpan data bahan resep
        val listBahan = ArrayList<Bahan>().apply {
            add(Bahan("Terong", 4, "buah"))
            add(Bahan("Cabe Rawit", 10, "buah"))
            add(Bahan("Bawang Putih", 6, "siung"))
            add(Bahan("Bawang Merah", 4, "siung"))
            add(Bahan("Garam", 1, "sdt"))
            add(Bahan("Gula Pasir", 1, "sdt"))
        }

        // Buat adapter untuk ListView menggunakan data binding
        val adapter = BahanArrayAdapter(this, R.layout.list_content, ArrayList(listBahan))

        // Set adapter ke ListView
        listView.adapter = adapter

        // Set adapter ke ListView
        listView.adapter = adapter
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