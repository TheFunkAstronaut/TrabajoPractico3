package com.example.trabajopractico3.ui

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import com.example.trabajopractico3.R
import com.example.viewmodels.PersonaViewModel
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {
    private lateinit var btnMisLikes: Button
    private lateinit var btnLike: Button
    private lateinit var btnDislike: Button
    private val viewModel: PersonaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnLike = findViewById(R.id.btnLike)
        btnMisLikes = findViewById(R.id.btnMisLikes)
        btnDislike = findViewById(R.id.btnDislike)

        setupEventListeners()
    }

    private fun setupEventListeners() {
        btnMisLikes.setOnClickListener {
            val navController = findNavController(R.id.fragmentContainerView)
            navController.navigate(R.id.action_fragmentMain_to_fragmentListaPersona)
        }

        btnLike.setOnClickListener {
            viewModel.likePersona()
        }

        btnDislike.setOnClickListener {
            viewModel.dislikePersona()
        }
    }
}
