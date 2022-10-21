package com.jonathanmojica.todo_realtime.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.jonathanmojica.todo_realtime.databinding.ItemSubtaskBinding
import com.jonathanmojica.todo_realtime.model.Subtarea

class SubtaskAdapter(var arrayList: ArrayList<Subtarea> = arrayListOf()): RecyclerView.Adapter<SubtaskAdapter.SubtaskHolder>() {

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
        holder.bind(arrayList[position])
    }

    override fun getItemCount(): Int{
        Log.d("Subtask","${arrayList.size}")
        return arrayList.size
    }

}