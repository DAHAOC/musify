package com.musify.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.musify.model.Playlist

class HomeViewModel : ViewModel() {

    private val _playlists = MutableLiveData<List<Playlist>>(listOf(
        Playlist(1, "Mi Playlist", "Usuario", "url1"),
        Playlist(2, "Mi Playlist", "Usuario", "url2"),
        Playlist(3, "Mi Playlist", "Usuario", "url3")
    ))
    val playlists: LiveData<List<Playlist>> = _playlists

    fun addPlaylist(playlist: Playlist) {
        val current = _playlists.value.orEmpty()
        _playlists.value = current + playlist
    }

    fun removePlaylist(id: Int) {
        val current = _playlists.value.orEmpty()
        _playlists.value = current.filter { it.id != id }
    }
}
