package com.muhammadfarhaan.apps.masakuy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.muhammadfarhaan.apps.masakuy.R
import com.muhammadfarhaan.apps.masakuy.model.DataResep
import com.squareup.picasso.Picasso

class HomeAdapter(cont: Context, data:ArrayList<DataResep>): RecyclerView.Adapter<HomeAdapter.HomeHolder>() {
    private val context: Context
    private val dataReseps:ArrayList<DataResep>
    private var ref: DatabaseReference

    init{
        context = cont
        dataReseps = data
        ref = FirebaseDatabase.getInstance().reference.child("Resep")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_masakan_home, parent, false)
        return HomeAdapter.HomeHolder(view)
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        Picasso.get().load(dataReseps[position].image).into(holder.himage)
    }

    override fun getItemCount(): Int {
        return  dataReseps.size
    }

    class HomeHolder(@NonNull itemView: View): RecyclerView.ViewHolder(itemView) {
        internal var himage: ImageView
        init{
            himage = itemView.findViewById(R.id.home_img_masakan)
        }
    }
}