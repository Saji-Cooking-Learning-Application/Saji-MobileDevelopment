package com.dicoding.sajiapps.home.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.dicoding.sajiapps.ViewModelFactory
import com.dicoding.sajiapps.databinding.FragmentHomeBinding
import com.dicoding.sajiapps.detail.DetailResepActivity
import com.dicoding.sajiapps.response.DataItem
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private val viewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        (activity as? AppCompatActivity)?.supportActionBar?.hide()
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        with(binding){
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    val searchQuery = searchBar.text.toString()
                    viewModel.searchFood(searchQuery)
                    true
                }
        }
        viewModel.listFood.observe(viewLifecycleOwner){
            setFood(it as ArrayList<DataItem>)
        }
        lifecycleScope.launch {
            viewModel.getAllFood()
        }

        return root
    }

    private fun setFood(foodItems: ArrayList<DataItem>){
        val adapter = FoodAdapter(foodItems)

        // Mengubah LinearLayoutManager menjadi StaggeredGridLayoutManager
        val spanCount = 2 // Ganti dengan jumlah kolom yang diinginkan
        val layoutManager = StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL)
        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        binding.rvListFood.layoutManager = layoutManager

        binding.rvListFood.setHasFixedSize(true)
        binding.rvListFood.adapter = adapter
        adapter.setOnItemCallback(object : FoodAdapter.OnItemClickCallback{
            override fun onItemClicked(data: DataItem) {
                val id = data.id
                Intent(this@HomeFragment.requireContext(), DetailResepActivity::class.java).also { intent ->
                    // Misalkan data yang ingin Anda sertakan ke DetailResepActivity adalah ID dan nama dari DataItem
                    intent.putExtra(DetailResepActivity.EXTRA_ID, id)
                    intent.putExtra(DetailResepActivity.IMG, data.foto)
                    intent.putExtra(DetailResepActivity.EXTRA_MENU, data.namaMenu)
                    startActivity(intent)
                }
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}