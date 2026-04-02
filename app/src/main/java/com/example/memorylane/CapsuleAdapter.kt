package com.example.memorylane

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CapsuleAdapter(
    private val list: List<Capsule>,
    private val onItemClick: (Capsule) -> Unit
) : RecyclerView.Adapter<CapsuleAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.title)
        val date = itemView.findViewById<TextView>(R.id.date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_capsule, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val capsule = list[position]

        holder.title.text = capsule.title

        val currentTime = System.currentTimeMillis()

        //LOCK SYSTEM
        if (capsule.openDate > currentTime) {
            holder.date.text = "🔒 Locked"
        } else {
            holder.date.text = "Open"
        }

        //Click only If Open
        holder.itemView.setOnClickListener {
            if (capsule.openDate <= currentTime) {
                onItemClick(capsule)
            }
        }
    }
}