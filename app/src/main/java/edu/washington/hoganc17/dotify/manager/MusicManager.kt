package edu.washington.hoganc17.dotify.manager

import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider

class MusicManager {

    var listOfSongs: List<Song>
    var currSong: Song? = null
    var songPlayedListener: SongPlayedListener? = null

    init {
        listOfSongs = SongDataProvider.getAllSongs()
    }

    fun updateList(newList: List<Song>) {
        listOfSongs = newList
    }

    fun playSong(song: Song) {
        currSong = song
        songPlayedListener?.onSongPlayed(song)
    }
}

