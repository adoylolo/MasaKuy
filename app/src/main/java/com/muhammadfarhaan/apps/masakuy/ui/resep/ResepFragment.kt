package com.muhammadfarhaan.apps.masakuy.ui.resep

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.muhammadfarhaan.apps.masakuy.R
import com.muhammadfarhaan.apps.masakuy.adapter.ResepAdapter
import com.muhammadfarhaan.apps.masakuy.model.DataResep

@Suppress("DEPRECATED_IDENTITY_EQUALS")
class ResepFragment : Fragment() {

    lateinit var r_Recycler : RecyclerView
    lateinit var r_Database : DatabaseReference
    lateinit var r_list: ArrayList<DataResep>
    var r_adapter: ResepAdapter? = null
    lateinit var search_button: ImageButton
    lateinit var search_text: EditText

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

        search_button = view.findViewById(R.id.search_btn)
        search_text = view.findViewById(R.id.search_resep)

        search_text.setOnKeyListener(object: View.OnKeyListener {
            override fun onKey(view:View, keyCode:Int, keyevent:KeyEvent):Boolean {
                //If the keyevent is a key-down event on the "enter" button
                if ((keyevent.action === KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER))
                {
                    val searchText = search_text.text.toString()
                    search(searchText)
                    return true
                }
                return false
            }
        })

        search_button.setOnClickListener(View.OnClickListener {
            val searchText = search_text.text.toString()
            search(searchText)
        })
    }

    private fun search(searchText : String) {
        val querySearch = r_Database.orderByChild("nama").startAt(searchText).endAt(searchText + "\uf8ff")
        val mContext = activity!!.applicationContext

        querySearch.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                r_list = ArrayList()
                for (dataSnapshot2 in dataSnapshot.children) {
                    val rsp2 = dataSnapshot2.getValue(DataResep::class.java)
                    r_list.add(rsp2!!)
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

    fun onKey(view:View, keyCode:Int, event:KeyEvent):Boolean {
        if ((keyCode == EditorInfo.IME_ACTION_SEARCH ||
                    keyCode == EditorInfo.IME_ACTION_DONE ||
                    (event.getAction() === KeyEvent.ACTION_DOWN && event.getKeyCode() === KeyEvent.KEYCODE_ENTER)))
        {
            val searchText = search_text.text.toString()
            search(searchText)
        }
        return false
    }
}