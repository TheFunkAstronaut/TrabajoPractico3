package com.example.trabajopractico3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajopractico3.R
import com.example.trabajopractico3.adapters.LikesAdapter
import com.example.viewmodels.PersonaViewModel

class FragmentListaPersona : Fragment() {

    private lateinit var btnVolver: Button
    private lateinit var recyclerViewLikes: RecyclerView
    private val viewModel: PersonaViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lista_persona, container, false)
        btnVolver = view.findViewById(R.id.btnVolver)
        recyclerViewLikes = view.findViewById(R.id.recyclerViewLikes)

        recyclerViewLikes.layoutManager = LinearLayoutManager(context)
        val adapter = LikesAdapter() // Asegúrate de pasar la lista de likes al adaptador
        recyclerViewLikes.adapter = adapter

        // Observamos los cambios en la lista de likes
        viewModel.likes.observe(viewLifecycleOwner) { likes ->
            adapter.submitList(likes)
        }

        setupEventListeners()
        return view
    }

    private fun setupEventListeners() {
        btnVolver.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_fragmentListaPersona_to_fragmentMain)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentListaPersona()
    }
}
