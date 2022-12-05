package com.example.toolshop.Views.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.toolshop.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment() {

    lateinit var firebaseAuth: FirebaseAuth
    val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("User")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_profile, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuth= Firebase.auth
        val user= firebaseAuth.currentUser
        //user --> email y password --> porque es el acceso al Firestore Auth
        val name= view.findViewById<EditText>(R.id.nameProfile)
        val email= view.findViewById<EditText>(R.id.emailProfile)
        val birth= view.findViewById<EditText>(R.id.birthProfile)
        email.setText(user?.email.toString())

        //database -- name y birthday -- Porque es la referencia a la base de datos en Realtime DataBase
        database.child(user?.uid.toString()).addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                name.setText(snapshot.child("name").value.toString())
                birth.setText(snapshot.child("birthday").value.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}