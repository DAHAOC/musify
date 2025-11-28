package com.musify.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.button.MaterialButton
import com.musify.R
import com.musify.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    // Example data
    private val allSongs = listOf(
        "Bohemian Rhapsody", "Shape of You", "Blinding Lights",
        "Levitating", "Bad Habits", "Peaches", "Stay", "Watermelon Sugar"
    )
    private val allUsers = listOf(
        "Alice Johnson", "Bob Smith", "Charlie Brown",
        "Diana Prince", "Ethan Hunt", "Fiona Gallagher"
    )

    private var currentList = mutableListOf<String>()
    private lateinit var adapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Default: show all songs + users
        currentList.addAll(allSongs + allUsers)
        adapter = SearchAdapter(currentList.toMutableList(), textColor = android.R.color.white)
        binding.searchRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.searchRecyclerView.adapter = adapter

        // Filter button listeners
        binding.btnSongs.setOnClickListener {
            toggleFilter(binding.btnSongs, binding.btnUsers, allSongs)
        }

        binding.btnUsers.setOnClickListener {
            toggleFilter(binding.btnUsers, binding.btnSongs, allUsers)
        }

        // Search input listener
        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString().lowercase()
                val filtered = currentList.filter { it.lowercase().contains(query) }
                adapter.updateList(filtered)
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun toggleFilter(selected: MaterialButton, unselected: MaterialButton, filterList: List<String>) {
        val wasSelected = selected.tag as? Boolean ?: false

        if (wasSelected) {
            // unselect: show both
            selected.tag = false
            selected.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.dark_gray))
            currentList.clear()
            currentList.addAll(allSongs + allUsers)
        } else {
            // select this one, unselect the other
            selected.tag = true
            selected.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.highlight_searchpage))
            unselected.tag = false
            unselected.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.dark_gray))
            currentList.clear()
            currentList.addAll(filterList)
        }

        adapter.updateList(currentList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
