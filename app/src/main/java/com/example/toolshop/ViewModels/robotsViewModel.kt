package com.example.toolshop.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.toolshop.Domain.repository
import com.example.toolshop.Models.robots

class robotsViewModel:ViewModel() {
    val repository= repository()
    fun fetchRobotData():LiveData<MutableList<robots>>{
        val mutableData= MutableLiveData<MutableList<robots>>()
        repository.getRobotsData().observeForever{
            mutableData.value = it
        }
        return mutableData
    }
}