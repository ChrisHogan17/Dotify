package edu.washington.hoganc17.dotify.manager

import edu.washington.hoganc17.dotify.model.Song

interface SongPlayedListener {
        fun onSongPlayed(song: Song)
}
