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

class ResepAdapter(cont:Context, data:ArrayList<DataResep>): RecyclerView.Adapter<ResepAdapter.ResepHolder>() {
    private val context:Context
    private val dataReseps:ArrayList<DataResep>
    private var ref: DatabaseReference

    init{
        context = cont
        dataReseps = data
        ref = FirebaseDatabase.getInstance().reference.child("Resep")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResepHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_detail_resep, parent, false)
        return ResepHolder(view)
    }

    override fun onBindViewHolder(holder: ResepHolder, position: Int) {
        holder.vnama.text = dataReseps[position].nama
        holder.vdesc.text = dataReseps[position].desc
        Picasso.get().load(dataReseps[position].image).into(holder.vimage)
    }

    override fun getItemCount(): Int {
        return  dataReseps.size
    }

    class ResepHolder(@NonNull itemView: View):RecyclerView.ViewHolder(itemView) {
        internal var vnama: TextView
        internal var vdesc: TextView
        internal var vimage: ImageView
        init{
            vnama = itemView.findViewById(R.id.txt_title_resep)
            vdesc = itemView.findViewById(R.id.txt_desc_resep)
            vimage = itemView.findViewById(R.id.img_resep)
        }
    }
}