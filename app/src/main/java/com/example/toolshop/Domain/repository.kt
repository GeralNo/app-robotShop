package com.example.toolshop.Domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.toolshop.Models.robots
import com.google.firebase.firestore.FirebaseFirestore

class repository {
    fun getRobotsData():LiveData<MutableList<robots>>{
        val mutableLiveData= MutableLiveData<MutableList<robots>>()
        FirebaseFirestore.getInstance().collection("Robots").get().addOnSuccessListener{
            result -> val listData= mutableListOf<robots>()
            for (document in result){
                val model= document.getString("model")
                val type= document.getString("type")
                val company= document.getString("company")
                val price= document.getLong("price")?.toInt()
                val url= document.getString("url")
                val robot= robots(model,type,company,price,url)
                listData.add(robot)
            }
            mutableLiveData.value=listData
        }
        return mutableLiveData
    }
}