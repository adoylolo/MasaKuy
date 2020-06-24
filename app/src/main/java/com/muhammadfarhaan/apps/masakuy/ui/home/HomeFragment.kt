package com.muhammadfarhaan.apps.masakuy.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.muhammadfarhaan.apps.masakuy.R
import com.muhammadfarhaan.apps.masakuy.adapter.HomeAdapter
import com.muhammadfarhaan.apps.masakuy.adapter.ResepAdapter
import com.muhammadfarhaan.apps.masakuy.model.DataResep

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    lateinit var h_Recycler : RecyclerView
    lateinit var h_Database : DatabaseReference
    lateinit var h_list: ArrayList<DataResep>
    var h_adapter: HomeAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mContext = activity!!.applicationContext

        h_Database = FirebaseDatabase.getInstance().getReference("Resep")
        h_Recycler = view.findViewById(R.id.recycler_home)
        h_Recycler.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        h_Recycler.layoutManager = layoutManager

        h_Database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                h_list = ArrayList()
                for (dataSnapshot2 in dataSnapshot.children) {
                    val hm = dataSnapshot2.getValue(DataResep::class.java)
                    h_list.add(hm!!)
                }
                h_adapter = HomeAdapter(mContext, h_list)
                h_Recycler.adapter = h_adapter
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(mContext, "Terjadi kesalahan", Toast.LENGTH_LONG)
                    .show()
            }
        })
    }
}