package com.example.managementstudent.presentation.ui.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.managementstudent.data.model.Grade
import com.example.managementstudent.databinding.ItemClassBinding

class ClassAdapter(val clickItem: (Grade)-> Unit) : RecyclerView.Adapter<ClassAdapter.ClassViewHolder>() {
    inner class ClassViewHolder(val viewBinding : ItemClassBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(grade : Grade) {
            viewBinding.txtGrade.text = grade.nameGrade
            viewBinding.txtYear.text = grade.year
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassViewHolder {
        val view = ItemClassBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ClassViewHolder(view)
    }

    override fun getItemCount(): Int {
        return diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: ClassViewHolder, position: Int) {
        val grade = diffUtil.currentList[position]
        grade.apply {
            holder.itemView.setOnClickListener {
                clickItem.invoke(this)
            }
        }

    }
   // companion object {
        val diff = object : DiffUtil.ItemCallback<Grade>(){
            override fun areItemsTheSame(oldItem: Grade, newItem: Grade): Boolean {
//                TODO("Not yet implemented")
                return oldItem.idGrade == newItem.idGrade
            }

            override fun areContentsTheSame(oldItem: Grade, newItem: Grade): Boolean {
               return oldItem == newItem
            }

        }
  //  }
    val diffUtil = AsyncListDiffer(this,diff)

}