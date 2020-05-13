package edu.washington.hoganc17.dotify.manager

import edu.washington.hoganc17.dotify.model.Song

class MusicManager (songList: List<Song>) {

    var listOfSongs: List<Song> = songList
    var currSong: Song? = null
    var songPlayedListener: SongPlayedListener? = null

    fun updateList(newList: List<Song>) {
        listOfSongs = newList
    }

    fun playSong(song: Song) {
        currSong = song
        songPlayedListener?.onSongPlayed(song)
    }
}

