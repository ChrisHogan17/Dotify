package edu.washington.hoganc17.dotify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import edu.washington.hoganc17.dotify.SongPlayerActivity.Companion.SONG_KEY
import edu.washington.hoganc17.dotify.model.SongAdapter
import kotlinx.android.synthetic.main.activity_song_list.*

class SongListActivity : AppCompatActivity() {

    lateinit var currSong: Song

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)
        title = "All Songs"

        val songList = SongDataProvider.getAllSongs()
        var miniPlayerEmpty = true

        val songAdapter =
            SongAdapter(songList)

        songAdapter.onSongClickListener = { song ->
            tvMiniPlayer.text = getString(R.string.miniPlayerText).format(song.title, song.artist)
            currSong = song
            miniPlayerEmpty = false
        }

        btnShuffle.setOnClickListener {
            val newSongList = songList.shuffled()
            songAdapter.change(newSongList)
            rvSongs.scrollToPosition(0)
        }

        miniPlayer.setOnClickListener {
            if(!miniPlayerEmpty) {
                val intent = Intent(this, SongPlayerActivity::class.java)
                intent.putExtra(SONG_KEY, currSong)
                startActivity(intent)
            }
        }

        rvSongs.adapter = songAdapter

    }
}
