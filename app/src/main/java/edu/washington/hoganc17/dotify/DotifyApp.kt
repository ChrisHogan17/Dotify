package edu.washington.hoganc17.dotify

import android.app.Application
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider

class DotifyApp: Application() {

    lateinit var listOfSongs: List<Song>
    var currSong: Song? = null
    var songPlayedListener: SongPlayedListener? = null


    override fun onCreate() {
        super.onCreate()

        listOfSongs = SongDataProvider.getAllSongs()
    }

    fun updateList(newList: List<Song>) {
        listOfSongs = newList
    }

    fun onSongPlayed(song: Song) {
        currSong = song
        songPlayedListener?.onSongPlayed(song)
    }

    interface SongPlayedListener {
        fun onSongPlayed(song: Song)
    }
}