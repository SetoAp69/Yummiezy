package com.excal.yummiezy.data

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler


import com.excal.yummiezy.databinding.ItemRowInstructionBinding

//var listener:onItemClick?=null
private lateinit var binding: ItemRowInstructionBinding

class InstructionAdapter(private val listInstruction:ArrayList<Instruction>): RecyclerView.Adapter<InstructionAdapter.ListViewHolder>() {
    inner class ListViewHolder(binding:ItemRowInstructionBinding):RecyclerView.ViewHolder(binding.root) {
        val tvInstructionNumber:TextView=binding.tvInstructionNumber
        val tvInstruction:TextView=binding.tvIntruction
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        binding=ItemRowInstructionBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return  ListViewHolder(binding)
    }



    override fun getItemCount(): Int {
        return listInstruction.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val(value)=listInstruction[position]
        holder.tvInstructionNumber.text = if (position < 9) (position + 1).toString()+". " else (position + 1).toString()+"."
        holder.tvInstruction.text=value
    }
}