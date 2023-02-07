package com.example.dnd_character_profiles.main_page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.dnd_character_profiles.R

class Details : Fragment() {
        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {
        val view =  inflater.inflate(R.layout.fragment_details, container, false)


        val args = this.arguments
        var nameData : String? = args?.getString("name")
        var firstClassData : String? = args?.getString("class")
        var raceData : String? = args?.getString("race")
        var lvlData : String? = args?.getString("lvl")
        var descriptionData : String? = args?.getString("description")


        val spinnerRace : Spinner = view.findViewById(R.id.spinnerRace)
        val spinnerClass : Spinner = view.findViewById(R.id.spinnerClass)
        val spinnerLvl : Spinner = view.findViewById(R.id.spinnerLvl)

        //val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, R.array.characterRaces)
        //spinnerRace.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, R.array.characterRaces)

        spinnerRace.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(adapterView: AdapterView<*>?, view1: View?, position: Int, id: Long) {
                        raceData = adapterView?.getItemAtPosition(position).toString()
                        updateData(view, nameData, raceData, firstClassData, lvlData, descriptionData)

                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                        raceData = ""
                        updateData(view, nameData, raceData, firstClassData, lvlData, descriptionData)

                }

        }
        spinnerClass.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(adapterView: AdapterView<*>?, view1: View?, position: Int, id: Long) {
                        firstClassData = adapterView?.getItemAtPosition(position).toString()
                        updateData(view, nameData, raceData, firstClassData, lvlData, descriptionData)

                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                        firstClassData = ""
                        updateData(view, nameData, raceData, firstClassData, lvlData, descriptionData)

                }

        }
        spinnerLvl.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(adapterView: AdapterView<*>?, view1: View?, position: Int, id: Long) {
                        lvlData = adapterView?.getItemAtPosition(position).toString()
                        updateData(view, nameData, raceData, firstClassData, lvlData, descriptionData)

                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                        lvlData = ""
                        updateData(view, nameData, raceData, firstClassData, lvlData, descriptionData)

                }

        }
        updateData(view, nameData, raceData, firstClassData, lvlData, descriptionData)

        val button = view.findViewById<Button>(R.id.submitButton)
        button.setOnClickListener{
                findNavController().navigate(R.id.action_details_to_listOfProfiles)
                val bundle = Bundle()
                /*bundle.putInt("position", args?.getInt("position")!!)
                bundle.putString("name", nameData)
                bundle.putString("race", raceData)
                bundle.putString("class", firstClassData)
                bundle.putString("lvl", lvlData)
                bundle.putString("description", descriptionData)*/
                //findNavController().navigate(R.id.action_details_to_listOfProfiles, bundle)
        }

        return view

        }
        fun updateData(view: View, nameData: String?, raceData:String?, firstClassData: String?, lvlData: String?, descriptionData: String?){

                val name = view.findViewById<TextView>(R.id.character_name)
                val race = view.findViewById<TextView>(R.id.character_race)
                val firstClass = view.findViewById<TextView>(R.id.character_firstclass)
                val lvl = view.findViewById<TextView>(R.id.character_lvl)
                val description = view.findViewById<TextView>(R.id.character_description)
                name.text = nameData
                race.text = "Rasa: " + raceData
                firstClass.text = "Klasa " + firstClassData
                lvl.text = "Poziom " + lvlData
                description.text = descriptionData
        }
}