package com.muhammadfarhaan.apps.masakuy.ui.minuman

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.muhammadfarhaan.apps.masakuy.R
import com.muhammadfarhaan.apps.masakuy.adapter.MinumanAdapter
import com.muhammadfarhaan.apps.masakuy.model.DataResep

class MinumanFragment : Fragment() {

    lateinit var r_Recycler : RecyclerView
    lateinit var r_Database : DatabaseReference
    lateinit var r_list: ArrayList<DataResep>
    var r_adapter: MinumanAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.makanan_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mContext = activity!!.applicationContext

        r_Database = FirebaseDatabase.getInstance().getReference("Resep")
        val query: Query = r_Database.orderByChild("kategori").equalTo("minuman")
        r_Recycler = view.findViewById(R.id.recycler_makanan)
        r_Recycler.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(context,2)
        r_Recycler.layoutManager = layoutManager

        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                r_list = ArrayList()
                for (dataSnapshot1 in dataSnapshot.children) {
                    val rsp = dataSnapshot1.getValue(DataResep::class.java)
                    r_list.add(rsp!!)
                }
                r_adapter = MinumanAdapter(mContext, r_list)
                r_Recycler.adapter = r_adapter
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(mContext, "Terjadi kesalahan", Toast.LENGTH_LONG)
                    .show()
            }
        })
    }
}