package edu.washington.hoganc17.dotify.model

data class SongPackage (
    val title: String,
    val numOfSongs: Int,
    val songs: List<Song>
)