package com.example.toolshop.Views.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.toolshop.R
import com.example.toolshop.ViewModels.robotsViewModel
import com.example.toolshop.Views.adapter.RobotAdapter

class RobotsFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: RobotAdapter
    val viewmodel by lazy { ViewModelProvider(this).get(robotsViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_robots, container, false)
        recyclerView= view.findViewById(R.id.rvRobots)
        adapter= RobotAdapter(requireContext())
        recyclerView.layoutManager=LinearLayoutManager(context)
        recyclerView.adapter=adapter
        observeData()
        return view
    }

    fun observeData(){
        viewmodel.fetchRobotData().observe(viewLifecycleOwner, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }
}