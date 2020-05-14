package edu.washington.hoganc17.dotify.manager

import edu.washington.hoganc17.dotify.model.Song

interface SongsFetchListener {
    fun onSongsFetched(fetchedSongList: List<Song>)
    fun onFetchError()
}