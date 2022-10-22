package com.jonathanmojica.todo_realtime.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jonathanmojica.todo_realtime.databinding.ItemPostickBinding
import com.jonathanmojica.todo_realtime.model.Categoria
import com.jonathanmojica.todo_realtime.model.Postick
import com.jonathanmojica.todo_realtime.model.Subtarea

class PostickAdapter(var arrayList: ArrayList<Postick> = arrayListOf(),var listener: (Postick) -> Unit): RecyclerView.Adapter<PostickAdapter.PostickHolder>()
{

    class PostickHolder(val postickBinding: ItemPostickBinding) : RecyclerView.ViewHolder(postickBinding.root) {
        private lateinit var adapter: SubtaskAdapter
        private lateinit var adapterCategory: CategoryAdapter

        fun bind(item: Postick,context: Context) {
            adapter = SubtaskAdapter(item.subtareas as ArrayList<Subtarea>)
            adapterCategory = CategoryAdapter(item.categorias as ArrayList<Categoria>)
            postickBinding.recyclerSubTask.adapter = adapter
            postickBinding.recyclerSubTask.layoutManager = LinearLayoutManager(context)
            postickBinding.recyclerCategory.adapter = adapterCategory
            postickBinding.recyclerCategory.layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
            postickBinding.postick = item
            if(item.subtareas.isEmpty())
            {
                postickBinding.viewLine.visibility = View.GONE
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostickHolder {
        val rootView = ItemPostickBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostickHolder(rootView)
    }

    override fun onBindViewHolder(holder: PostickHolder, position: Int) {
        holder.postickBinding.postickId.setOnClickListener{
            listener(arrayList[position])
        }
        holder.bind(arrayList[position],holder.itemView.context)
    }

    override fun getItemCount(): Int{
        return arrayList.size
    }

}