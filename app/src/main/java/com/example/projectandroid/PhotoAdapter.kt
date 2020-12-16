package com.example.projectandroid

import android.view.LayoutInflater
import android.view.ViewGroup
//import android.widget.ListAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.projectandroid.databinding.GridViewItemBinding
import com.example.projectandroid.dataclass.Restaurant


class PhotoAdapter(private val onClickListener : OnClickListener) : ListAdapter<Restaurant, PhotoAdapter.PropertiesViewHolder> (DiffCallback){
    class PropertiesViewHolder(private var binding: GridViewItemBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(restaurants: Restaurant){
            binding.properties = restaurants
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Restaurant>() {
        override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
            return oldItem.id == newItem.id
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): PropertiesViewHolder {
        return PropertiesViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PropertiesViewHolder, position: Int) {
       val restaurantProperties = getItem(position)
        holder.itemView.setOnClickListener{
            onClickListener.onClick(restaurantProperties)
        }
        holder.bind(restaurantProperties)
    }
    class OnClickListener(val clickListener: (restaurant:Restaurant) -> Unit){
        fun onClick(restaurant: Restaurant) = clickListener(restaurant)
    }


}