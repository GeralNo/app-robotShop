package com.example.toolshop.Views.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.toolshop.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RestoreActivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restore)

        firebaseAuth= Firebase.auth
        val email= findViewById<EditText>(R.id.restoreEmail)
        val restore= findViewById<Button>(R.id.restore)

        restore.setOnClickListener {
            restorePassword(email.text.toString())
        }
    }

    fun restorePassword(email:String){

        firebaseAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener(this){
                    Task->if(Task.isSuccessful){
                Toast.makeText(baseContext,"Password changed",Toast.LENGTH_LONG).show()
                startActivity(Intent(this,LoginActivity::class.java))
            }else{
                Toast.makeText(baseContext,"ERROR",Toast.LENGTH_LONG).show()
            }
            }
    }
}