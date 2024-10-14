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


    // Inicializamos con una lista de personas de ejemplo
    init {
        _personas.value = listOf(
            Persona("Alexis Andrews", listOf(R.drawable.alexis_a1, R.drawable.alexis_a2, R.drawable.alexis_a3)),
            Persona("Violet Myers", listOf(R.drawable.violet1, R.drawable.violet2, R.drawable.violet3)))
        _indiceActual.value = 0 // Inicia en la primera persona
    }

    fun likePersona() {
        val indice = _indiceActual.value ?: return
        val persona = _personas.value?.get(indice) ?: return

        // Agregar el like solo si no está ya en la lista
        if (_likes.value?.none { it.nombre == persona.nombre } == true) {
            val newLike = Like(persona.nombre, persona.fotos.first()) // Usamos la primera foto
            val updatedLikes = _likes.value?.toMutableList() ?: mutableListOf() // Crea una lista mutable
            updatedLikes.add(newLike) // Agrega el nuevo like
            _likes.value = updatedLikes // Actualiza LiveData
        }

        _indiceActual.value = indice + 1
        if (_indiceActual.value!! >= _personas.value!!.size) {
            _indiceActual.value = 0 // Vuelve al inicio si llega al final
        }
    }


    fun dislikePersona() {
        val lista = _personas.value?.toMutableList() ?: return // Asegúrate de que lista no sea nula
        val indice = _indiceActual.value ?: return

        if (lista.isNotEmpty()) {
            lista.removeAt(indice)
            _personas.value = lista // Esto debería funcionar ahora sin errores

            // Ajustar el índice actual si es necesario
            if (indice >= lista.size) {
                _indiceActual.value = 0 // Reinicia el índice si se eliminó la última persona
            } else {
                _indiceActual.value = indice // Mantiene el mismo índice
            }
        }

        // Eliminar el like correspondiente
        _likes.value?.let { currentLikes ->
            val updatedLikes = currentLikes.filter { it.nombre != lista[indice].nombre } // Elimina el like de la persona que se eliminó
            _likes.value = updatedLikes // Actualiza LiveData
        }
    }

}
