package com.example.dnd_character_profiles.main_page

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dnd_character_profiles.R

class LOPAdapter(private var profilesList: ArrayList<CharacterProfile>) : RecyclerView.Adapter<LOPAdapter.ViewHolder>() {
    private lateinit var mOnClickListener: ListItemClickListener
    interface ListItemClickListener {
        fun onListItemClick(position: Int)
    }

    fun setOnListItemClickListener(listener: ListItemClickListener) {
        mOnClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return ViewHolder(itemView, mOnClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentProfile = profilesList[position]
        holder.name.text = currentProfile.name
        holder.race.text = currentProfile.race
        holder.firstclass.text = currentProfile.firstclass
        holder.lvl.text = currentProfile.lvl
    }

    override fun getItemCount(): Int {
        return profilesList.size
    }

    class ViewHolder(itemView : View, listener: ListItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val name : TextView = itemView.findViewById(R.id.character_name)
        val race : TextView = itemView.findViewById(R.id.character_race)
        val firstclass : TextView = itemView.findViewById(R.id.character_firstclass)
        val lvl : TextView = itemView.findViewById(R.id.character_lvl)

        init {
            itemView.setOnClickListener {
                listener.onListItemClick(adapterPosition)
            }
        }
    }
}