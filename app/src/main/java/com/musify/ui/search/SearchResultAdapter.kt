package com.musify.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.musify.R
import com.musify.model.SearchResult

// Adapter encargado de mostrar la lista de resultados de búsqueda en el RecyclerView.
class SearchResultAdapter(
    private var items: List<SearchResult>, private val onItemClick: (SearchResult) -> Unit
) : RecyclerView.Adapter<SearchResultHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_search_result, parent, false)
        return SearchResultHolder(view, onItemClick)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SearchResultHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    fun updateList(newList: List<SearchResult>) {
        items = newList
        notifyDataSetChanged()
    }
}
