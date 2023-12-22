package com.dicoding.sajiapps.detail
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.dicoding.sajiapps.databinding.ItemResepBinding
// Ganti dengan package name yang sesuai
import java.util.ArrayList
class BahanArrayAdapter(context: Context, resource: Int, private val listBahan: ArrayList<Bahan>) :
    ArrayAdapter<Bahan>(context, resource, listBahan) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: ItemResepBinding

        if (convertView == null) {
            val inflater = LayoutInflater.from(context)
            binding = ItemResepBinding.inflate(inflater, parent, false)
            binding.root.tag = binding
        } else {
            binding = convertView.tag as ItemResepBinding
        }

        val bahan = getItem(position)
        binding.namaBahan.text = bahan?.nama
        binding.takaran.text = bahan?.takaran.toString()
        binding.unit.text = bahan?.unit

        return binding.root
    }
}