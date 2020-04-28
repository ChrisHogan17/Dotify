package edu.washington.hoganc17.dotify.model

import com.ericchee.songdataprovider.Song

interface OnSongClickListener {
    fun onSongClicked(song: Song)
}