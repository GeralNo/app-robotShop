package com.example.toolshop.Views.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.toolshop.R
import com.example.toolshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Asociar el activity a un layout
        //setContentView(R.layout.activity_main)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //configuracion para que la animacion funcione
        binding.animationView.setAnimation(R.raw.robot_app)
        binding.animationView.playAnimation()
        //transicion entre la animacion y la siguiente actividad (4s)
        handler= Handler(Looper.myLooper()!!)
        handler.postDelayed({
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
        },4000)

    }
}