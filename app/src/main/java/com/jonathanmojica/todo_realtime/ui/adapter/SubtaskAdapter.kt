package com.jonathanmojica.todo_realtime.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase

import com.jonathanmojica.todo_realtime.databinding.ItemSubtaskBinding
import com.jonathanmojica.todo_realtime.model.PostickData
import com.jonathanmojica.todo_realtime.model.Subtarea

class SubtaskAdapter(var arrayList: PostickData): RecyclerView.Adapter<SubtaskAdapter.SubtaskHolder>() {


    private val marksRef = FirebaseDatabase.getInstance().getReference("todos")
    class SubtaskHolder(val taskBinding: ItemSubtaskBinding) : RecyclerView.ViewHolder(taskBinding.root) {

        fun bind(item: Subtarea) {
            Log.d("Subtask","Creado")
            taskBinding.subtask = item

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubtaskHolder {
        Log.d("Subtask","Creado")
        val rootView = ItemSubtaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SubtaskHolder(rootView)
    }

    override fun onBindViewHolder(holder: SubtaskHolder, position: Int) {
        holder.bind(arrayList.postick!!.subtareas!![position])
        holder.taskBinding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            marksRef.child(arrayList.uid!!).child("subtareas").child(position.toString()).child("activo").setValue(isChecked)
        }

    }

    override fun getItemCount(): Int{
        Log.d("Subtask","${arrayList.postick!!.subtareas!!.size}")
        return arrayList.postick!!.subtareas!!.size
    }

}