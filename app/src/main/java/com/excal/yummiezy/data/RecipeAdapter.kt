package com.excal.yummiezy.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.excal.yummiezy.databinding.ActivityMainBinding
import com.excal.yummiezy.databinding.ActivityRecipeDetailBinding
import com.excal.yummiezy.databinding.ItemRowIngredientBinding
import com.excal.yummiezy.databinding.ItemRowRecipeBinding

class RecipeAdapter(private val listRecipe:ArrayList<Recipe>): RecyclerView.Adapter<RecipeAdapter.ListViewHolder>(){
    var listener : onItemClick?=null
    private lateinit var binding: ItemRowRecipeBinding

    inner class ListViewHolder(binding:ItemRowRecipeBinding):RecyclerView.ViewHolder(binding.root) {
        val imgPhoto:ImageView=binding.ivRecipe
        val tvTitle: TextView =binding.tvTitle

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        binding=ItemRowRecipeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ListViewHolder(binding)

    }

    override fun getItemCount(): Int {
      return listRecipe.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
       val (name,photo)=listRecipe[position]
        holder.tvTitle.text=name
        holder.imgPhoto.setImageResource(photo)
        holder.itemView.setOnClickListener{
            listener?.setOnItemClick(it,listRecipe[position])
        }
    }

    interface onItemClick{
        fun setOnItemClick(view: View, recipe:Recipe)
    }

}