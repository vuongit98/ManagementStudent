package com.example.managementstudent.presentation.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.managementstudent.data.model.Subject
import com.example.managementstudent.databinding.ItemSubjectBinding

class SubjectAdapter(val context: Context, val itemClick: (Subject) -> Unit) : RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder>(){

    inner class SubjectViewHolder(val viewBinding : ItemSubjectBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(subject: Subject, context : Context) {
            Glide.with(context).load(subject.imgSubject).into(viewBinding.imgSubject)
            viewBinding.txtSubject.text = subject.nameSubject
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view = ItemSubjectBinding.inflate(LayoutInflater.from(parent.context), parent, false) ;
        return SubjectViewHolder(view)
    }

    override fun getItemCount(): Int {
        return diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        val subject = diffUtil.currentList[position]
        subject?.let {
            holder.bind(it,context)
            holder.itemView.setOnClickListener {
                itemClick.invoke(subject)
            }
        }
    }
    val diff = object : DiffUtil.ItemCallback<Subject>() {
        override fun areItemsTheSame(oldItem: Subject, newItem: Subject): Boolean {
            return oldItem.idSubject == newItem.idSubject
        }

        override fun areContentsTheSame(oldItem: Subject, newItem: Subject): Boolean {
            return oldItem == newItem
        }

    }
    val diffUtil = AsyncListDiffer(this, diff)
}