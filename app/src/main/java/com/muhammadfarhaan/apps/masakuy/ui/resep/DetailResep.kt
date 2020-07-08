package com.muhammadfarhaan.apps.masakuy.ui.resep

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.muhammadfarhaan.apps.masakuy.R
import com.squareup.picasso.Picasso

class DetailResep : AppCompatActivity() {

    lateinit var button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_resep)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val pToolbar = findViewById<Toolbar>(R.id.toolbar_detail_resep)
        pToolbar.title = ("Detail Resep")
        pToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)

        pToolbar.setNavigationOnClickListener {
            finish()
            overridePendingTransition(R.anim.noanim,R.anim.animate_slide_up_exit)
        }

        getIncomingIntent()
    }

    private fun getIncomingIntent() {
        if (intent.hasExtra("nama_masakan") && intent.hasExtra("image_masakan") && intent.hasExtra("bahan_masakan") && intent.hasExtra("langkah_masakan")){

            val namaMasakan = intent.getStringExtra("nama_masakan")
            val imageMasakan = intent.getStringExtra("image_masakan")
            val bahanMasakan = intent.getStringExtra("bahan_masakan")
            val langkahMasakan = intent.getStringExtra("langkah_masakan")
            val test = "https://www.youtube.com/"

            button = findViewById(R.id.btn_yutup)
            button.setOnClickListener(View.OnClickListener {
                val uri = Uri.parse(test)
                val likeIng = Intent(Intent.ACTION_VIEW, uri)

                likeIng.setPackage("com.youtube.android")

                try {
                    startActivity(likeIng)
                } catch (e: ActivityNotFoundException) {
                    startActivity(Intent(Intent.ACTION_VIEW,
                        Uri.parse(
                            ""+uri
                        )))
                }
            })

            setMasakan(namaMasakan, imageMasakan, bahanMasakan, langkahMasakan)
        }
    }

    private fun setMasakan(namaMasakan: String, imageMasakan: String, bahanMasakan: String, langkahMasakan: String) {

        val image = findViewById<ImageView>(R.id.img_detail_masakan)
        Picasso.get().load(imageMasakan).into(image)

        val name = findViewById<TextView>(R.id.txt_detail_resep)
        name.text = namaMasakan

        val bahan = findViewById<TextView>(R.id.txt_bahan_detail)
        bahan.text = bahanMasakan

        val langkah = findViewById<TextView>(R.id.txt_langkah_detail)
        langkah.text = langkahMasakan
    }

}