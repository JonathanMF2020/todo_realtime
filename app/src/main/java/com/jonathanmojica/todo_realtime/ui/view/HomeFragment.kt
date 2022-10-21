package com.jonathanmojica.todo_realtime.ui.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jonathanmojica.todo_realtime.R
import com.jonathanmojica.todo_realtime.databinding.FragmentHomeBinding
import com.jonathanmojica.todo_realtime.model.Postick
import com.jonathanmojica.todo_realtime.model.Subtarea
import com.jonathanmojica.todo_realtime.ui.adapter.PostickAdapter

class HomeFragment : Fragment() {

    private lateinit var adapter: PostickAdapter
    private lateinit var binding: FragmentHomeBinding
    private val array = arrayListOf(
        Postick(Color.RED,"Clases", arrayListOf(), arrayListOf(
            Subtarea("Hacer tarea",true),
            Subtarea("Hacer tarea 2",true),
        )),
        Postick(Color.BLACK,"Trabajo", arrayListOf(), arrayListOf()),
        Postick(Color.BLUE,"Deportes", arrayListOf(), arrayListOf()),
    )



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setRecycler()
        return binding.root
    }

    private fun setRecycler() {
        adapter = PostickAdapter(array) {

        }

        binding.recyclerPostick.adapter = adapter
        binding.recyclerPostick.layoutManager = LinearLayoutManager(activity)
    }

}