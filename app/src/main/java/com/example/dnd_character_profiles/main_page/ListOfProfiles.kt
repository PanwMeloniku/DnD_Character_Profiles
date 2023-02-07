package com.example.dnd_character_profiles.main_page

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dnd_character_profiles.R

class ListOfProfiles : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: LOPAdapter
    private lateinit var data : ArrayList<CharacterProfile>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        data = ArrayList()
        for (i in 1 .. 10)
        {
            data.add(CharacterProfile("A_" + i.toString(), "human", "fighter", "12", ""))
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {
        val view = inflater.inflate(R.layout.fragment_list_of_profiles, container, false)

        val args = this.arguments
        if (args?.getInt("position") != null) {
            data[args?.getInt("position")!!].name = args.getString("name").toString()
            data[args?.getInt("position")!!].firstclass = args.getString("class").toString()
            data[args?.getInt("position")!!].race = args.getString("race").toString()
            data[args?.getInt("position")!!].lvl = args.getString("lvl").toString()
            data[args?.getInt("position")!!].description = args.getString("description").toString()
        }

        recyclerView = view.findViewById(R.id.ProfilesRecycleView)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        adapter = LOPAdapter(data)
        recyclerView.adapter = adapter
        adapter.setOnListItemClickListener(object: LOPAdapter.ListItemClickListener{
            override fun onListItemClick(position: Int) {
                val bundle = Bundle()
                bundle.putInt("position", position)
                bundle.putString("name", data[position].name)
                bundle.putString("race", data[position].race)
                bundle.putString("class", data[position].firstclass)
                bundle.putString("lvl", data[position].lvl)
                bundle.putString("description", data[position].description)
                findNavController().navigate(R.id.action_listOfProfiles_to_details, bundle)
            }
        })
        return view
    }
}