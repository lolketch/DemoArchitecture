package com.example.feature_detail

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.api.MyCharacter

class DetailFragment: Fragment(R.layout.fragment_detail) {
    companion object {
        const val USER_DATA = "USER_DATA"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val character = arguments?.get(USER_DATA) as String

        view.findViewById<TextView>(R.id.nameCharacter).text = character
    }
}