package com.example.foodtrck.ui.foodtrucks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodtrck.data.model.FoodTruck
import com.example.foodtrck.databinding.FoodtruckListItemBinding

class FoodTruckListAdapter(private var listener: FoodTruckItemListener)
    : RecyclerView.Adapter<FoodTruckListAdapter.FoodTruckListViewHolder>() {

    interface  FoodTruckItemListener {
        fun onClickFoodTruck()
    }

    private val foodtrucks = ArrayList<FoodTruck>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FoodTruckListAdapter.FoodTruckListViewHolder {

        val binding: FoodtruckListItemBinding =
            FoodtruckListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return  FoodTruckListViewHolder(binding, listener)
    }

    fun updateData(newList: List<FoodTruck>?) {
        if(newList == null) {
            return
        }

        foodtrucks.clear()
        foodtrucks.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int =
        foodtrucks.size

    override fun onBindViewHolder(
        holder: FoodTruckListAdapter.FoodTruckListViewHolder,
        position: Int
    ) {
        holder.bind(foodtrucks[position])
    }

    class FoodTruckListViewHolder(
        private val itemBinding: FoodtruckListItemBinding,
        private val listener: FoodTruckItemListener)
        : RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {

        private lateinit var foodTruck: FoodTruck

        init {
            itemBinding.root.setOnClickListener(this)
        }

        fun bind(item: FoodTruck) {
            foodTruck = item
            itemBinding.foodtruckName.text = foodTruck.name
        }

        override fun onClick(v: View?) {
            listener.onClickFoodTruck()
        }
    }
}