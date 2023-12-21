package com.dicoding.sajiapps.home.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.sajiapps.databinding.ItemMasakanBinding
import com.dicoding.sajiapps.response.DataItem

class FoodAdapter(private val listFood: List<DataItem>): ListAdapter<DataItem, FoodAdapter.FoodViewHolder>(DIFF_CALLBACK) {
    private var onItemClickCallback: OnItemClickCallback? = null
    inner class FoodViewHolder(private val binding: ItemMasakanBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(food: DataItem) {
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(food)
            }
            binding.apply {
                Glide.with(binding.root)
                    .load(food.foto).into(binding.imageView)
                tvJudul.text = food.namaMenu
            }
        }
    }

    fun setOnItemCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding = ItemMasakanBinding.inflate(LayoutInflater.from(parent.context))
        return FoodViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listFood.size
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = listFood[position]
        holder.bind(food)
    }
    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: DataItem,
                newItem: DataItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
    interface OnItemClickCallback{
        fun onItemClicked(data: DataItem)
    }
}


