package com.jonathanmojica.todo_realtime.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jonathanmojica.todo_realtime.databinding.ItemPostickBinding
import com.jonathanmojica.todo_realtime.model.Postick
import com.jonathanmojica.todo_realtime.model.Subtarea
import okhttp3.internal.notify

class PostickAdapter(var arrayList: ArrayList<Postick> = arrayListOf(),var listener: (Postick) -> Unit): RecyclerView.Adapter<PostickAdapter.PostickHolder>()
{

    class PostickHolder(val postickBinding: ItemPostickBinding) : RecyclerView.ViewHolder(postickBinding.root) {
        private lateinit var adapter: SubtaskAdapter

        fun bind(item: Postick,context: Context) {
            item.subtareas.forEach {
                Log.d("Subt",it.nombre)
            }
            adapter = SubtaskAdapter(item.subtareas as ArrayList<Subtarea>)
            postickBinding.recyclerSubTask.adapter = adapter
            postickBinding.recyclerSubTask.layoutManager = LinearLayoutManager(context)
            postickBinding.postick = item
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