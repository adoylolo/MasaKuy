package com.muhammadfarhaan.apps.masakuy.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.muhammadfarhaan.apps.masakuy.Interface.IRecyclerItemClickListener
import com.muhammadfarhaan.apps.masakuy.R
import com.muhammadfarhaan.apps.masakuy.model.DataResep
import com.muhammadfarhaan.apps.masakuy.ui.resep.DetailResep
import com.squareup.picasso.Picasso

class ResepAdapter(context:Context, data:ArrayList<DataResep>): RecyclerView.Adapter<ResepAdapter.ResepHolder>() {
    private val mContext: Context
    private val dataReseps:ArrayList<DataResep>
    private var ref: DatabaseReference

    init{
        mContext = context
        dataReseps = data
        ref = FirebaseDatabase.getInstance().getReference("Resep")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResepHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_detail_resep, parent, false)
        return ResepHolder(view)
    }

    override fun onBindViewHolder(holder: ResepHolder, position: Int) {
        holder.vnama.text = dataReseps[position].nama
        holder.vbahan.text = dataReseps[position].bahan
        holder.vlangkah.text = dataReseps[position].langkah
        holder.vurl.text = dataReseps[position].url
        Picasso.get().load(dataReseps[position].image).into(holder.vimage)

        holder.setRecyclerItemClickListener(object:IRecyclerItemClickListener {
            override fun onClick(view:View, position:Int) {
                Toast.makeText(mContext,"Masakan : " + dataReseps[position].nama ,Toast.LENGTH_SHORT).show()
                val intent = Intent(mContext, DetailResep::class.java)
                intent.putExtra("nama_masakan", dataReseps[position].nama)
                intent.putExtra("image_masakan", dataReseps[position].image)
                intent.putExtra("bahan_masakan", dataReseps[position].bahan)
                intent.putExtra("langkah_masakan", dataReseps[position].langkah)
                intent.putExtra("url_masakan", dataReseps[position].url)
                view.context.startActivity(intent)
            }
        })
    }

    override fun getItemCount(): Int {
        return  dataReseps.size
    }

    class ResepHolder(@NonNull itemView: View):RecyclerView.ViewHolder(itemView), View.OnClickListener {
        internal var vnama: TextView
        internal var vbahan: TextView
        internal var vlangkah: TextView
        internal var vurl: TextView
        internal var vimage: ImageView
        internal var parentLayout: RelativeLayout
        private var recyclerItemClickListener : IRecyclerItemClickListener? = null

        fun setRecyclerItemClickListener(recyclerItemClickListener:IRecyclerItemClickListener) {
            this.recyclerItemClickListener = recyclerItemClickListener
        }

        init{
            vnama = itemView.findViewById(R.id.txt_title_resep)
            vbahan = itemView.findViewById(R.id.txt_bahan_resep)
            vlangkah = itemView.findViewById(R.id.txt_langkah_resep)
            vurl = itemView.findViewById(R.id.txt_yutup_resep)
            vimage = itemView.findViewById(R.id.img_resep)
            parentLayout = itemView.findViewById(R.id.parent_layout)

            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            recyclerItemClickListener?.onClick(v, adapterPosition)
        }
    }
}