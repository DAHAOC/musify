package com.musify.ui.user

import com.musify.model.UserTrackResult

object TrackDataSource {
    val items: List<UserTrackResult> = listOf(
        UserTrackResult(
            1,
            "Canción 1",
            "https://cdn-images.dzcdn.net/images/cover/d03696568ffd1509019f7eff45f3218d/0x1900-000000-80-0-0.jpg"
        ), UserTrackResult(
            2,
            "Canción 2",
            "https://images.genius.com/610e9dcc458fb5736e853cc73a187cfe.300x300x1.png"
        )
    )
}