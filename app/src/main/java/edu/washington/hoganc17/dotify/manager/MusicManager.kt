package edu.washington.hoganc17.dotify.manager

import edu.washington.hoganc17.dotify.model.Song

class MusicManager (apiManager: ApiManager) {
    init {
        apiManager.fetchSongs(
            { retrievedSongs ->
                listOfSongs = retrievedSongs
                songsFetchListener?.onSongsFetched(listOfSongs)
            },
            {
                songsFetchListener?.onFetchError()
            }
        )
    }

    var listOfSongs: List<Song> = listOf()
    var currSong: Song? = null
    var songPlayedListener: SongPlayedListener? = null
    var songsFetchListener: SongsFetchListener? = null

    fun updateList(newList: List<Song>) {
        listOfSongs = newList
    }

    fun playSong(song: Song) {
        currSong = song
        songPlayedListener?.onSongPlayed(song)
    }
}

