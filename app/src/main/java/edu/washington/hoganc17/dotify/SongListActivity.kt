package edu.washington.hoganc17.dotify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ericchee.songdataprovider.SongDataProvider
import edu.washington.hoganc17.dotify.SongPlayerActivity.Companion.ALBUM_KEY
import edu.washington.hoganc17.dotify.SongPlayerActivity.Companion.ARTIST_KEY
import edu.washington.hoganc17.dotify.SongPlayerActivity.Companion.TITLE_KEY
import kotlinx.android.synthetic.main.activity_song_list.*

class SongListActivity : AppCompatActivity() {

    lateinit var currSong: String
    lateinit var currArtist: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)
        title = "All Songs"

        val songList = SongDataProvider.getAllSongs()
        var miniPlayerEmpty = true
        var currAlbum: Int? = null

        val songAdapter = SongAdapter(songList)

        songAdapter.onSongClickListener = { songTitle, artist, albumID ->
            tvMiniPlayer.text = getString(R.string.miniPlayerText).format(songTitle, artist)
            currSong = songTitle
            currArtist = artist
            currAlbum = albumID
            miniPlayerEmpty = false
        }

        btnShuffle.setOnClickListener {
            val newSongList = songList.shuffled()
            songAdapter.change(newSongList)
        }

        miniPlayer.setOnClickListener {
            if(!miniPlayerEmpty) {
                val intent = Intent(this, SongPlayerActivity::class.java)
                intent.putExtra(TITLE_KEY, currSong)
                intent.putExtra(ARTIST_KEY, currArtist)
                intent.putExtra(ALBUM_KEY, currAlbum)

                startActivity(intent)
            }
        }

        rvSongs.adapter = songAdapter

    }
}
