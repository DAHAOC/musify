package com.musify.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView


class SearchAdapter(
    private val items: MutableList<String>,
    private val textColor: Int = android.R.color.black
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView = view.findViewById(android.R.id.text1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        val holder = ViewHolder(view)
        holder.text.setTextColor(ContextCompat.getColor(parent.context, textColor))
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text.text = items[position]
    }

    override fun getItemCount(): Int = items.size

    fun updateList(newList: List<String>) {
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }
}
