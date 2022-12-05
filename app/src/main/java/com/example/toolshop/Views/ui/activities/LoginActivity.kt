package com.example.toolshop.Views.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.toolshop.R
import com.example.toolshop.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity: AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var signup: Button
    lateinit var home: Button
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding= ActivityMainBinding.inflate(layoutInflater)
        //setContentView(binding.root)
        setContentView(R.layout.activity_login)

        firebaseAuth= Firebase.auth
        val email= findViewById<EditText>(R.id.loginEmail)
        val password= findViewById<EditText>(R.id.loginPassword)

        home= findViewById(R.id.login)
        signup= findViewById(R.id.signup)
        home.setOnClickListener {
            login(email.text.toString(),password.text.toString())
        }

        signup.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
        }

        val txtNewPass= findViewById<TextView>(R.id.textViewForgotPassword)
        txtNewPass.setOnClickListener {
            startActivity(Intent(this,RestoreActivity::class.java))
        }
    }
    fun login(email:String,password:String){
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                    Task->if(Task.isSuccessful){
                startActivity(Intent(this, HomeActivity::class.java))
            }else{
                Toast.makeText(baseContext,"ERROR",Toast.LENGTH_LONG).show()
                }
            }
    }
}