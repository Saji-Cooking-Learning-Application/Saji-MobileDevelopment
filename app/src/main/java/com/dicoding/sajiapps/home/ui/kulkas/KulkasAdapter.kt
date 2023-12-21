package com.dicoding.sajiapps.home.ui.kulkas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.sajiapps.databinding.ItemKulkasBinding
import com.dicoding.sajiapps.response.DataItemBahan

class KulkasAdapter(private val listKulkas: List<DataItemBahan>): ListAdapter<DataItemBahan, KulkasAdapter.KulkasViewHolder>(
    DIFF_CALLBACK) {
    private var onItemClickCallback: OnItemClickCallback? = null
    inner class KulkasViewHolder(private val binding: ItemKulkasBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(bahan: DataItemBahan){
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(bahan)
            }
            binding.apply {
                Glide.with(binding.root)
                    .load(bahan.foto).into(binding.imageRecipe)
                textRecipeTitle.text = bahan.namaBahan
            }
        }
    }
    fun setOnItemCallback(onItemClickCallback: KulkasAdapter.OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KulkasViewHolder {
        val binding = ItemKulkasBinding.inflate(LayoutInflater.from(parent.context))
        return KulkasViewHolder(binding)
    }

    override fun onBindViewHolder(holder: KulkasViewHolder, position: Int) {
        val kulkas = listKulkas[position]
        holder.bind(kulkas)
    }
    override fun getItemCount(): Int {
        return listKulkas.size
    }

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItemBahan>() {
            override fun areItemsTheSame(oldItem: DataItemBahan, newItem: DataItemBahan): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: DataItemBahan,
                newItem: DataItemBahan
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
    interface OnItemClickCallback{
        fun onItemClicked(data: DataItemBahan)
    }
}