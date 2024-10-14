package com.example.trabajopractico3.ui

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import com.example.trabajopractico3.R

class MainActivity : AppCompatActivity() {
    private lateinit var btnMisLikes:Button
    private lateinit var btnLike:Button
    private lateinit var btnDislike:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnMisLikes = findViewById(R.id.btnMisLikes)
        setupEventListeners()
    }

    private fun setupEventListeners() {
        btnMisLikes.setOnClickListener {
            val navController = findNavController(R.id.fragmentContainerView)
            navController.navigate(R.id.action_fragmentMain_to_fragmentListaPersona)
        }
    }
}