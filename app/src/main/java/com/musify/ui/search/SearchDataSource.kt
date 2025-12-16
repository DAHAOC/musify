package com.musify.ui.search

import com.musify.model.SearchResult
import com.musify.model.SearchResultType

object SearchDataSource {
    val items: List<SearchResult> = listOf(
        SearchResult(
            "Moscow Mule",
            "https://i.scdn.co/image/ab67616d0000b27349d694203245f241a1bcaa72",
            "Bad Bunny",
            SearchResultType.TRACK
        ), SearchResult(
            "Como Antes",
            "https://i.scdn.co/image/ab67616d0000b273519266cd05491a5b5bc22d1e",
            "Bad Bunny",
            SearchResultType.TRACK
        ), SearchResult(
            "Dua Lipa", "https://picsum.photos/202", "", SearchResultType.USER
        ), SearchResult(
            "Quevedo",
            "https://akamai.sscdn.co/uploadfile/letras/fotos/1/c/4/1/1c41718dc8bd31b7bfdc49e4d1d10be8.jpg",
            "",
            SearchResultType.USER
        ), SearchResult(
            "La Última",
            "https://cdn-images.dzcdn.net/images/artist/79880cc1b999b15567e332203464c34e/1900x1900-000000-81-0-0.jpg",
            "",
            SearchResultType.TRACK
        )
    )
}