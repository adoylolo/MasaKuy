package com.muhammadfarhaan.apps.masakuy.ui.resep

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.muhammadfarhaan.apps.masakuy.R

class ResepFragment : Fragment() {

    private lateinit var resepViewModel: ResepViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        resepViewModel =
            ViewModelProviders.of(this).get(ResepViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_resep, container, false)
        return root
    }
}