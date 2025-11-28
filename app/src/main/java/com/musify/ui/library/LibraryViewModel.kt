package com.musify.ui.library

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.musify.model.Playlist

class LibraryViewModel : ViewModel() {
    private val _playlists = MutableLiveData(listOf(
        Playlist(1, "Un Verano Sin Ti", "Bad Bunny", "https://i.scdn.co/image/ab67616d0000b27349d694203245f241a1bcaa72"),
        Playlist(2, "DtMF", "Bad Bunny", "https://images.genius.com/3b00c9850faf570161794846d22e0611.1000x1000x1.png"),
        Playlist(3, "Donde quiero estar", "Quevedo", "https://i.scdn.co/image/ab67616d00001e02d53036b726a5a5a1cda5e891")
    ))

    val playlists: MutableLiveData<List<Playlist>> = MutableLiveData(_playlists.value?.toList())

    fun addPlaylist(playlist: Playlist) {
        val current = _playlists.value.orEmpty()
        _playlists.value = current + playlist
    }

    fun removePlaylist(id: Int) {
        val current = _playlists.value.orEmpty()
        _playlists.value = current.filter { it.id != id }
    }

    fun searchPlaylists(query: String) {
        val current = _playlists.value.orEmpty()
        val filter = if (query.isBlank()) {
            current
        } else {
            current.filter { it.title.contains(query, ignoreCase = true)
                    || it.owner.contains(query, ignoreCase = true) }
        }
        playlists.value = filter
    }
}
