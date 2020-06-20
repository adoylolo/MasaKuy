package com.muhammadfarhaan.apps.masakuy.ui.resep

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResepViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "RESEP"
    }
    val text: LiveData<String> = _text
}