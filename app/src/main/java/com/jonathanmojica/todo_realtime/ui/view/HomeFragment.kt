package com.jonathanmojica.todo_realtime.ui.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.jonathanmojica.todo_realtime.databinding.FragmentHomeBinding
import com.jonathanmojica.todo_realtime.model.Postick
import com.jonathanmojica.todo_realtime.model.PostickData
import com.jonathanmojica.todo_realtime.model.Subtarea
import com.jonathanmojica.todo_realtime.ui.adapter.PostickAdapter

class HomeFragment : Fragment() {

    private lateinit var adapter: PostickAdapter
    private lateinit var binding: FragmentHomeBinding
    private val marksRef = FirebaseDatabase.getInstance().getReference("todos")
    private var array:ArrayList<PostickData> = arrayListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setRecycler()
        listener()
        return binding.root
    }

    private fun listener(){
        marksRef.addChildEventListener(object : ChildEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("HomeFragment", databaseError.toException().toString())
            }
            override fun onChildMoved(dataSnapshot: DataSnapshot, previousName: String?) {
                Log.d("HomeFragment", "Moved: $dataSnapshot")
            }
            @SuppressLint("NotifyDataSetChanged")
            override fun onChildChanged(dataSnapshot: DataSnapshot, previousName: String?) {
                Log.d("HomeFragment", "Changed: $dataSnapshot")
                var ch = dataSnapshot.getValue(Postick::class.java)

                var postickData = PostickData(dataSnapshot.key,ch)
                array.forEach{
                    if(it.uid == dataSnapshot.key)
                    {
                        it.postick!!.subtareas = postickData.postick!!.subtareas
                    }
                }
                adapter.arrayList = array
                adapter.notifyDataSetChanged()
            }
            @SuppressLint("NotifyDataSetChanged")
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                Log.d("HomeFragment", "Remove: $dataSnapshot")
                var ch = dataSnapshot.getValue(Postick::class.java)
                var postickData = PostickData(dataSnapshot.key,ch)
                array.remove(postickData)
                adapter.arrayList = array
                adapter.notifyDataSetChanged()

            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onChildAdded(dataSnapshot: DataSnapshot, p1: String?) {
                Log.d("HomeFragment", "Added: $dataSnapshot")
                var ch = dataSnapshot.getValue(Postick::class.java)
                var postickData = PostickData(dataSnapshot.key,ch)
                array.add(postickData)
                adapter.arrayList = array
                adapter.notifyDataSetChanged()

            }
        })
    }

    private fun setRecycler() {
        adapter = PostickAdapter(array) {

        }
        binding.recyclerPostick.adapter = adapter
        binding.recyclerPostick.layoutManager = LinearLayoutManager(activity)
    }

}