package com.muhammadfarhaan.apps.masakuy.ui.resep

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.muhammadfarhaan.apps.masakuy.R
import com.muhammadfarhaan.apps.masakuy.adapter.ResepAdapter
import com.muhammadfarhaan.apps.masakuy.model.DataResep

class ResepFragment : Fragment() {

    lateinit var r_Recycler : RecyclerView
    lateinit var r_Database : DatabaseReference
    lateinit var r_list: ArrayList<DataResep>
    var r_adapter: ResepAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_resep, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mContext = activity!!.applicationContext

        r_Database = FirebaseDatabase.getInstance().getReference("Resep")
        r_Recycler = view.findViewById(R.id.recycler_resep)
        r_Recycler.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(context,2)
        r_Recycler.layoutManager = layoutManager

        r_Database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                r_list = ArrayList()
                for (dataSnapshot1 in dataSnapshot.children) {
                    val rsp = dataSnapshot1.getValue(DataResep::class.java)
                    r_list.add(rsp!!)
                }
                r_adapter = ResepAdapter(mContext, r_list)
                r_Recycler.adapter = r_adapter
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(mContext, "Terjadi kesalahan", Toast.LENGTH_LONG)
                    .show()
            }
        })
    }
}