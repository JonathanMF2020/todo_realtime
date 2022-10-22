package com.jonathanmojica.todo_realtime.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jonathanmojica.todo_realtime.databinding.ItemCategoryBinding
import com.jonathanmojica.todo_realtime.model.Categoria

class CategoryAdapter(var arrayList: ArrayList<Categoria> = arrayListOf()): RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {

    class CategoryHolder(val categoryBinding: ItemCategoryBinding) : RecyclerView.ViewHolder(categoryBinding.root) {
        fun bind(item: Categoria) {
            categoryBinding.category = item
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val rootView = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryAdapter.CategoryHolder(rootView)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}