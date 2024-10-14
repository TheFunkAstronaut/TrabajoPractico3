package com.example.trabajopractico3.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.trabajopractico3.R
import com.example.trabajopractico3.adapters.FotosAdapter
import com.example.viewmodels.PersonaViewModel

class FragmentMain : Fragment() {

    private lateinit var viewPagerFotos: ViewPager2
    private lateinit var textViewNombre: TextView
    private val viewModel: PersonaViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        // Referencias a las vistas
        viewPagerFotos = view.findViewById(R.id.viewPagerFotos)
        textViewNombre = view.findViewById(R.id.textViewNombre)

        // Observamos los cambios en la persona actual
        viewModel.indiceActual.observe(viewLifecycleOwner, Observer { indice ->
            val persona = viewModel.personas.value?.get(indice)
            persona?.let {
                textViewNombre.text = it.nombre
                val adapter = FotosAdapter(it.fotos, it.nombre) // Pasa el nombre aquí
                viewPagerFotos.adapter = adapter
                setupPhotoBar(it.fotos.size)
            }
        })


        // Registro del callback para actualizar la barra de fotos
        viewPagerFotos.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updatePhotoBar(position) // Cambia el segmento según la foto actual
            }
        })

        return view
    }

    private fun setupPhotoBar(numPhotos: Int) {
        val photoBar = view?.findViewById<LinearLayout>(R.id.photoBar)
        photoBar?.removeAllViews() // Limpiamos la barra por si se vuelve a cargar

        for (i in 0 until numPhotos) {
            val segment = View(requireContext())
            val layoutParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT)
            layoutParams.weight = 1f // Esto hace que cada segmento sea del mismo tamaño
            layoutParams.marginEnd = 4 // Espacio entre segmentos
            segment.layoutParams = layoutParams

            // Colocamos un color inicial para cada segmento
            segment.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.segment_inactive))

            // Agregamos el segmento a la barra
            photoBar?.addView(segment)
        }
    }

    private fun updatePhotoBar(selectedIndex: Int) {
        val photoBar = view?.findViewById<LinearLayout>(R.id.photoBar)
        val numSegments = photoBar?.childCount ?: return

        for (i in 0 until numSegments) {
            val segment = photoBar?.getChildAt(i)
            if (i == selectedIndex) {
                // Cambia el color del segmento seleccionado
                segment?.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.segment_active))
            } else {
                // Restaura el color de los segmentos no seleccionados
                segment?.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.segment_inactive))
            }
        }
    }

}


