package com.example.trabajopractico3.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.trabajopractico3.R
import android.widget.TextView
import com.example.trabajopractico3.adapters.FotosAdapter
import com.example.trabajopractico3.modelos.Persona

class FragmentMain : Fragment() {

    private lateinit var viewPagerFotos: ViewPager2
    private lateinit var textViewNombre: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        // Referencias a las vistas
        viewPagerFotos = view.findViewById(R.id.viewPagerFotos)
        textViewNombre = view.findViewById(R.id.textViewNombre)

        // Simulación de una persona
        val persona = Persona(
            nombre = "Alexis Andrews",
            fotos = listOf(
                R.drawable.alexis_a1,
                R.drawable.alexis_a2,
                R.drawable.alexis_a3
            )
        )

        // Configurar el nombre y el ViewPager2
        textViewNombre.text = persona.nombre
        val adapter = FotosAdapter(persona.fotos)
        viewPagerFotos.adapter = adapter

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentMain()
    }
}
