package com.dicoding.sajiapps.home.ui.kulkas

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.dicoding.sajiapps.ViewModelFactory
import com.dicoding.sajiapps.databinding.FragmentKulkasBinding
import com.dicoding.sajiapps.detail.DetailResepActivity
import com.dicoding.sajiapps.home.ui.home.FoodAdapter
import com.dicoding.sajiapps.home.ui.home.HomeViewModel
import com.dicoding.sajiapps.response.DataItem
import com.dicoding.sajiapps.response.DataItemBahan
import kotlinx.coroutines.launch

class KulkasFragment : Fragment() {
    private val viewModel by viewModels<KulkasViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }
//    private var mList = ArrayList<KulkasData>()
    private var _binding: FragmentKulkasBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var adapter: KulkasAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentKulkasBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.rvListFood.setHasFixedSize(true)
        binding.rvListFood.layoutManager = LinearLayoutManager(requireContext())
        viewModel.listFood.observe(viewLifecycleOwner){
            setFood(it as ArrayList<DataItemBahan>)
        }
        lifecycleScope.launch {
            viewModel.getAllBahan()
        }


//        val textView: TextView = binding.textNotifications
        return root
    }
    private fun setFood(foodItems: ArrayList<DataItemBahan>){
        val adapter = KulkasAdapter(foodItems)

        // Mengubah LinearLayoutManager menjadi StaggeredGridLayoutManager
        val spanCount = 2 // Ganti dengan jumlah kolom yang diinginkan
        val layoutManager = StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL)
        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        binding.rvListFood.layoutManager = layoutManager

        binding.rvListFood.setHasFixedSize(true)
        binding.rvListFood.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}