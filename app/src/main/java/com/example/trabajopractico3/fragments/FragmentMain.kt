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

        viewPagerFotos = view.findViewById(R.id.viewPagerFotos)
        textViewNombre = view.findViewById(R.id.textViewNombre)

        viewModel.indiceActual.observe(viewLifecycleOwner, Observer { indice ->
            val persona = viewModel.personas.value?.get(indice)
            persona?.let {
                textViewNombre.text = it.nombre
                val adapter = FotosAdapter(it.fotos, it.nombre)
                viewPagerFotos.adapter = adapter
                setupPhotoBar(it.fotos.size)
            }
        })

        viewPagerFotos.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updatePhotoBar(position)
            }
        })

        return view
    }

    private fun setupPhotoBar(numPhotos: Int) {
        val photoBar = view?.findViewById<LinearLayout>(R.id.photoBar)
        photoBar?.removeAllViews()

        for (i in 0 until numPhotos) {
            val segment = View(requireContext())
            val layoutParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT)
            layoutParams.weight = 1f
            layoutParams.marginEnd = 4
            segment.layoutParams = layoutParams

            segment.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.segment_inactive))

            photoBar?.addView(segment)
        }
    }

    private fun updatePhotoBar(selectedIndex: Int) {
        val photoBar = view?.findViewById<LinearLayout>(R.id.photoBar)
        val numSegments = photoBar?.childCount ?: return

        for (i in 0 until numSegments) {
            val segment = photoBar?.getChildAt(i)
            if (i == selectedIndex) {
                segment?.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            } else {
                segment?.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.segment_inactive))
            }
        }
    }

}


