package com.example.toolshop.Views.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController
import com.example.toolshop.R

class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cardRobot = view.findViewById<CardView>(R.id.card_robot)

        cardRobot.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_robotsFragment)
        }

        val cardShopping = view.findViewById<CardView>(R.id.card_shopping)

        cardShopping.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_shoppingFragment)
        }

        val cardProfile = view.findViewById<CardView>(R.id.card_profile)

        cardProfile.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_profileFragment)
        }

        val cardFavourites = view.findViewById<CardView>(R.id.card_favourites)

        cardFavourites.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_favouritesFragment)
        }

        val cardShipping = view.findViewById<CardView>(R.id.card_shipping)

        cardShipping.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_shippingFragment)
        }

        val cardAssistance = view.findViewById<CardView>(R.id.card_help)

        cardAssistance.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_assistanceFragment)
        }
    }

}