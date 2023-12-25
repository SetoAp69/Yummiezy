package com.excal.yummiezy.data

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.excal.yummiezy.databinding.ItemRowIngredientBinding

//var listener:onItemClick?=null
private lateinit var binding: ItemRowIngredientBinding


class IngredientAdapter(private val listIngredient:ArrayList<Ingredient>): RecyclerView.Adapter<IngredientAdapter.ListViewHolder>() {
    private var serving: Int = 1
    inner class ListViewHolder(binding:ItemRowIngredientBinding):RecyclerView.ViewHolder(binding.root) {
        val tvAmount:TextView=binding.tvIngredientAmount
        val tvValue:TextView=binding.tvIngredientValue
    }

    fun setServing(serving:Int){
        this.serving=serving
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        binding=ItemRowIngredientBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return  ListViewHolder(binding)
    }



    override fun getItemCount(): Int {
        return listIngredient.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val(amount, value)=listIngredient[position]
        holder.tvAmount.text=(amount.toDouble()*serving).toString()
        holder.tvValue.text=value
    }
}