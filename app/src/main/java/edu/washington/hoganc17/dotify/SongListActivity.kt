package edu.washington.hoganc17.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ericchee.songdataprovider.SongDataProvider
import kotlinx.android.synthetic.main.activity_song_list.*

class SongListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)
        title = "All Songs"

        val songList = SongDataProvider.getAllSongs()

        val songAdapter = SongAdapter(songList)
        songAdapter.onSongClickListener = { songTitle, artist ->
            tvMiniPlayer.text = getString(R.string.miniPlayerText).format(songTitle, artist)
        }

        btnShuffle.setOnClickListener {
            val newSongList = songList.shuffled()
            songAdapter.change(newSongList)
        }

        rvSongs.adapter = songAdapter

    }
}
