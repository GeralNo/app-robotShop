package com.example.toolshop.Views.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.toolshop.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SignUpActivity: AppCompatActivity() {
    lateinit var home: Button
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var dbReference: DatabaseReference

    lateinit var name: EditText
    lateinit var birthday:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singup)

        firebaseAuth= Firebase.auth
        dbReference= Firebase.database.reference.child("User")

        home= findViewById(R.id.register)
        name= findViewById(R.id.singupName)
        val email= findViewById<EditText>(R.id.singupEmail)
        birthday= findViewById(R.id.singupBirthday)
        val password= findViewById<TextView>(R.id.singupPassword)

        home.setOnClickListener {
            createUser(email.text.toString(),password.text.toString())
        }
    }

    private fun createUser(email:String, password:String){
        val na:String= name.text.toString()
        val bi:String= birthday.text.toString()
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this){
            Task->if (Task.isSuccessful){
                val user=firebaseAuth.currentUser
            val userdb= dbReference.child(user?.uid.toString())
            userdb.child("name").setValue(na)
            userdb.child("birthday").setValue(bi)

                startActivity(Intent(this,LoginActivity::class.java))
            }
            else{
                Toast.makeText(applicationContext,"This user cannot be registered",Toast.LENGTH_LONG).show()
            }
        }
    }
}