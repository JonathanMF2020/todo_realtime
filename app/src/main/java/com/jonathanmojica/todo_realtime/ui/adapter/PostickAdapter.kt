package com.jonathanmojica.todo_realtime.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import com.jonathanmojica.todo_realtime.databinding.ItemPostickBinding
import com.jonathanmojica.todo_realtime.model.PostickData
import com.jonathanmojica.todo_realtime.model.Subtarea

class PostickAdapter(var arrayList: ArrayList<PostickData> = arrayListOf(), var listener: (PostickData) -> Unit): RecyclerView.Adapter<PostickAdapter.PostickHolder>()
{

    class PostickHolder(val postickBinding: ItemPostickBinding) : RecyclerView.ViewHolder(postickBinding.root) {
        private lateinit var adapter: SubtaskAdapter
        private val marksRef = FirebaseDatabase.getInstance().getReference("todos")

        fun bind(item: PostickData,context: Context) {
            adapter = SubtaskAdapter(item)
            postickBinding.recyclerSubTask.adapter = adapter
            postickBinding.recyclerSubTask.layoutManager = LinearLayoutManager(context)
            postickBinding.txtTitulo.setOnClickListener{
                postickBinding.editChangeName.visibility = View.VISIBLE
                postickBinding.editChangeName.setText(item.postick!!.titulo)
                postickBinding.txtTitulo.visibility = View.GONE
            }
            postickBinding.editChangeName.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    marksRef.child(item.uid!!).child("titulo").setValue(postickBinding.editChangeName.text.toString())
                    item.postick!!.titulo = postickBinding.editChangeName.text.toString()
                    postickBinding.txtTitulo.visibility = View.VISIBLE
                    postickBinding.editChangeName.visibility = View.GONE
                    true
                }
                false
            }
            postickBinding.imgAdd.setOnClickListener{
                postickBinding.editTextTextPersonName.visibility = View.VISIBLE
                postickBinding.editTextTextPersonName.requestFocus()
            }
            postickBinding.editTextTextPersonName.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    var sub = Subtarea(postickBinding.editTextTextPersonName.text.toString(),true)
                    var array = item.postick!!.subtareas
                    array!!.add(sub)
                    marksRef.child(item.uid!!).child("subtareas").setValue(array)
                    postickBinding.editTextTextPersonName.setText("")
                    postickBinding.editTextTextPersonName.visibility = View.GONE
                    true
                }
                false
            }
            if(item.postick!!.subtareas!!.isEmpty())
            {
                postickBinding.viewLine.visibility = View.GONE
            }
            postickBinding.postick = item.postick
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