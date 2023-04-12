package com.example.dnd_character_profiles.main_page

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.dnd_character_profiles.R


class Details : Fragment() {
        val name by lazy { view?.findViewById<EditText>(R.id.character_name) }
        val race by lazy { view?.findViewById<TextView>(R.id.character_race) }
        val firstClass by lazy { view?.findViewById<TextView>(R.id.character_firstclass) }
        val lvl by lazy { view?.findViewById<TextView>(R.id.character_lvl) }
        val description by lazy { view?.findViewById<EditText>(R.id.character_description) }
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

                println(firstClassData)
                val spinnerRace : Spinner = view.findViewById(R.id.spinnerRace)
                val spinnerClass : Spinner = view.findViewById(R.id.spinnerClass)
                val spinnerLvl : Spinner = view.findViewById(R.id.spinnerLvl)

                //val nameET : EditText = view.findViewById(R.id.character_name)
                //val descriptionET : EditText = view.findViewById(R.id.character_description)


                println(nameData)
                println("Opis: " + descriptionData)

                val classList : List<String> = resources.getStringArray(R.array.characterClasses).toList()
                val raceList : List<String> = resources.getStringArray(R.array.characterRaces).toList()
                val lvlList : List<String> = resources.getStringArray(R.array.characterLevel).toList()

                spinnerRace.setSelection(raceList.indexOf(raceData))
                spinnerClass.setSelection(classList.indexOf(firstClassData))
                spinnerLvl.setSelection(lvlList.indexOf(lvlData))

                name?.addTextChangedListener(object : TextWatcher {
                        override fun afterTextChanged(s: Editable) {}
                        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

                        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                                if (s.length != 0) {nameData = s.toString()
                                        println(s.toString())}
                        }
                })/*addTextChangedListener {
                        println("Imie przed: " + nameData)
                        nameData = name.toString()
                        println("Imie po: " + nameData)
                        updateData(nameData, raceData, firstClassData, lvlData, descriptionData)
                }
                description?.addTextChangedListener {
                        descriptionData = description.toString()
                        updateData(nameData, raceData, firstClassData, lvlData, descriptionData)
                }*/

                spinnerRace.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                        override fun onItemSelected(adapterView: AdapterView<*>?, view1: View?, position: Int, id: Long) {
                                raceData = adapterView?.getItemAtPosition(position).toString()
                                updateData(nameData, raceData, firstClassData, lvlData, descriptionData)

                        }

                        override fun onNothingSelected(p0: AdapterView<*>?) {
                                raceData = ""
                                updateData(nameData, raceData, firstClassData, lvlData, descriptionData)
                        }

                }
                spinnerClass.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                        override fun onItemSelected(adapterView: AdapterView<*>?, view1: View?, position: Int, id: Long) {
                                firstClassData = adapterView?.getItemAtPosition(position).toString()
                                updateData(nameData, raceData, firstClassData, lvlData, descriptionData)
                        }

                        override fun onNothingSelected(p0: AdapterView<*>?) {
                                firstClassData = ""
                                updateData(nameData, raceData, firstClassData, lvlData, descriptionData)
                        }

                }
                spinnerLvl.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                        override fun onItemSelected(adapterView: AdapterView<*>?, view1: View?, position: Int, id: Long) {
                                lvlData = adapterView?.getItemAtPosition(position).toString()
                                updateData(nameData, raceData, firstClassData, lvlData, descriptionData)

                        }

                        override fun onNothingSelected(p0: AdapterView<*>?) {
                                lvlData = ""
                                updateData(nameData, raceData, firstClassData, lvlData, descriptionData)

                        }

                }
                //updateData(nameData, raceData, firstClassData, lvlData, descriptionData)

                val button = view.findViewById<Button>(R.id.submitButton)
                button.setOnClickListener{
                        //findNavController().navigate(R.id.action_details_to_listOfProfiles)
                        val bundle = Bundle()
                        bundle.putInt("position", args?.getInt("position")!!)
                        bundle.putString("name", nameData)
                        bundle.putString("race", raceData)
                        bundle.putString("class", firstClassData)
                        bundle.putString("lvl", lvlData)
                        bundle.putString("description", descriptionData)
                        findNavController().navigate(R.id.action_details_to_listOfProfiles, bundle)
                }

        return view

        }
        fun updateData(nameData: String?, raceData:String?, firstClassData: String?, lvlData: String?, descriptionData: String?){

                name?.setText(nameData)
                println("Co tu mamy? " + name?.text)
                race?.text = "Rasa: " + raceData
                firstClass?.text = "Klasa " + firstClassData
                lvl?.text = "Poziom " + lvlData
                description?.setText(descriptionData)
        }
}