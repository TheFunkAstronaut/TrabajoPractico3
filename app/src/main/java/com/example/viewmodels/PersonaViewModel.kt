package com.example.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trabajopractico3.R
import com.example.trabajopractico3.modelos.Like
import com.example.trabajopractico3.modelos.Persona

class PersonaViewModel : ViewModel() {

    private val _personas = MutableLiveData<List<Persona>>()
    val personas: LiveData<List<Persona>> get() = _personas

    private val _indiceActual = MutableLiveData<Int>()
    val indiceActual: LiveData<Int> get() = _indiceActual

    private val _likes = MutableLiveData<List<Like>>(emptyList())
    val likes: LiveData<List<Like>> get() = _likes

    init {
        _personas.value = listOf(
            Persona("F1nnster", listOf(R.drawable.femboy1, R.drawable.femboy2, R.drawable.femboy3)),
            Persona("Alexis Andrews", listOf(R.drawable.alexis_a1, R.drawable.alexis_a2, R.drawable.alexis_a3)),
            Persona("Violet Myers", listOf(R.drawable.violet3, R.drawable.violet2, R.drawable.violet1)),
            Persona("Mia Khalifa", listOf(R.drawable.mia1, R.drawable.mia2)),
            Persona("Angela White", listOf(R.drawable.angela1, R.drawable.angela2, R.drawable.angela3)),
            Persona("Kendra Lust", listOf(R.drawable.kendra1, R.drawable.kendra2, R.drawable.kendra3)),
            Persona("Lana Rhoades", listOf(R.drawable.lana1, R.drawable.lana2, R.drawable.lana3)),
            Persona("Eva Elfie", listOf(R.drawable.eva_elfie1, R.drawable.eva_elfie2, R.drawable.eva_elfie3)),
            Persona("Alexis Texas", listOf(R.drawable.alexis_t1, R.drawable.alexis_t2, R.drawable.alexis_t3)),
            Persona("Mia Malkova", listOf(R.drawable.malkova1, R.drawable.malkova2, R.drawable.malkova3)),
            Persona("Violet Myers", listOf(R.drawable.riley1, R.drawable.riley2)),
            Persona("Sasha Grey", listOf(R.drawable.sasha1, R.drawable.sasha2, R.drawable.sasha3)))
        _indiceActual.value = 0 
    }

    fun likePersona() {
        val indice = _indiceActual.value ?: return
        val persona = _personas.value?.get(indice) ?: return
        
        if (_likes.value?.none { it.nombre == persona.nombre } == true) {
            val newLike = Like(persona.nombre, persona.fotos.first()) 
            val updatedLikes = _likes.value?.toMutableList() ?: mutableListOf() 
            updatedLikes.add(newLike) 
            _likes.value = updatedLikes
        }
        _indiceActual.value = indice + 1
        if (_indiceActual.value!! >= _personas.value!!.size) {
            _indiceActual.value = 0 
        }
    }


    fun dislikePersona() {
        val lista = _personas.value?.toMutableList() ?: return 
        val indice = _indiceActual.value ?: return

        if (lista.isNotEmpty()) {
            lista.removeAt(indice)
            _personas.value = lista 
            
            if (indice >= lista.size) {
                _indiceActual.value = 0 
            } else {
                _indiceActual.value = indice
            }
        }

    }
}
