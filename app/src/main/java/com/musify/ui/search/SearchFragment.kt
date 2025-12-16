package com.musify.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.musify.R
import com.musify.model.SearchResultType

class SearchFragment : Fragment() {
    private lateinit var adapter: SearchResultAdapter
    private lateinit var recyclerView: RecyclerView

    private var isUserFilterOn: Boolean = false
    private var isTrackFilterOn: Boolean = false
    private var currentStringSearch: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.searchRecyclerView)

        adapter = SearchResultAdapter(SearchDataSource.items, { searchResult ->

        })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        fun updateResults() {
            adapter.updateList(
                SearchDataSource.items.filter { searchResult ->
                    (searchResult.type != SearchResultType.USER || isUserFilterOn) && (searchResult.type != SearchResultType.TRACK || isTrackFilterOn) && searchResult.title.contains(
                        currentStringSearch, ignoreCase = true
                    )
                })
        }

        val btnSongs = view.findViewById<MaterialButton>(R.id.btnSongs)
        val btnUsers = view.findViewById<MaterialButton>(R.id.btnUsers)
        val searchEditText = view.findViewById<android.widget.EditText>(R.id.searchEditText)

        btnSongs.setOnClickListener {
            isTrackFilterOn = !isTrackFilterOn
            updateResults()
        }

        btnUsers.setOnClickListener {
            isUserFilterOn = !isUserFilterOn
            updateResults()
        }

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                currentStringSearch = s.toString()
                updateResults()
            }
        })
    }
}
