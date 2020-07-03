package com.muhammadfarhaan.apps.masakuy.ui.resep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.muhammadfarhaan.apps.masakuy.R
import com.squareup.picasso.Picasso

class DetailResep : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_resep)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val pToolbar = findViewById<Toolbar>(R.id.toolbar_detail_resep)
        pToolbar.title = ("Picture")
        pToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)

        pToolbar.setNavigationOnClickListener {
            finish()
            overridePendingTransition(R.anim.noanim,R.anim.animate_slide_up_exit)
        }

        getIncomingIntent()
    }

    private fun getIncomingIntent() {
        if (intent.hasExtra("nama_masakan") && intent.hasExtra("image_masakan")) {

            val masakan = intent.getStringExtra("nama_masakan")
            val imageMasakan = intent.getStringExtra("image_masakan")

            setMasakan(masakan, imageMasakan)
        }
    }

    private fun setMasakan(masakan: String, imageMasakan: String) {

        val image = findViewById<ImageView>(R.id.img_detail_masakan)
        Picasso.get().load(imageMasakan).into(image)

        val name = findViewById<TextView>(R.id.txt_detail_resep)
        name.text = masakan
    }

}