package com.dicoding.sajiapps.home.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.sajiapps.R
import com.dicoding.sajiapps.databinding.FragmentKulkasBinding

class KulkasFragment : Fragment() {

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



//        val textView: TextView = binding.textNotifications
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}