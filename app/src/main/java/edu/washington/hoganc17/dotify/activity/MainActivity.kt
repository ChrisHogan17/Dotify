package edu.washington.hoganc17.dotify.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import edu.washington.hoganc17.dotify.R
import edu.washington.hoganc17.dotify.fragment.SongListFragment
import edu.washington.hoganc17.dotify.fragment.SongListFragment.Companion.ARG_SONG_LIST

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val masterSongList = SongDataProvider.getAllSongs() as ArrayList<Song>

        val songListFragment = SongListFragment()

        val argumentsBundle = Bundle().apply {
            putParcelableArrayList(ARG_SONG_LIST, masterSongList)
        }

        songListFragment.arguments = argumentsBundle

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragContainer, songListFragment)
            .commit()
    }
}
