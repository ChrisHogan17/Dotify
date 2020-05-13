package edu.washington.hoganc17.dotify.manager

import com.ericchee.songdataprovider.Song

interface SongPlayedListener {
        fun onSongPlayed(song: Song)
}
