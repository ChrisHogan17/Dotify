package edu.washington.hoganc17.dotify.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ericchee.songdataprovider.Song
import edu.washington.hoganc17.dotify.DotifyApp
import edu.washington.hoganc17.dotify.R
import edu.washington.hoganc17.dotify.fragment.NowPlayingFragment
import edu.washington.hoganc17.dotify.fragment.SongListFragment
import edu.washington.hoganc17.dotify.model.OnSongClickListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnSongClickListener, DotifyApp.SongPlayedListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dotifyApp = application as DotifyApp
        dotifyApp.songPlayedListener = this

        miniPlayer.visibility = View.GONE

        lateinit var songListFragment: SongListFragment
        if (supportFragmentManager.findFragmentByTag(NowPlayingFragment.TAG) == null) {
            songListFragment = SongListFragment()

            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragContainer, songListFragment, SongListFragment.TAG)
                .commit()

            miniPlayer.visibility = View.VISIBLE
        } else {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            val frag = supportFragmentManager.findFragmentByTag(SongListFragment.TAG) as? SongListFragment
            frag?.let {
                songListFragment = it
            }
        }

        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount > 0) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            } else {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            }
        }

        miniPlayer.setOnClickListener{
            val currSong = dotifyApp.currSong
            currSong?.apply {
                onMiniPlayerClicked(this)
            }
        }

        btnShuffle.setOnClickListener {
            val frag = supportFragmentManager.findFragmentByTag(SongListFragment.TAG) as? SongListFragment
            frag?.shuffleList()
        }
    }

    override fun onSongClicked(song: Song) {
        val dotifyApp = application as DotifyApp
        dotifyApp.onSongPlayed(song)
    }

    private fun updateSong(song: Song) {
        tvMiniPlayer.text = getString(R.string.miniPlayerText).format(song.title, song.artist)
    }

    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStack()
        miniPlayer.visibility = View.VISIBLE
        return super.onNavigateUp()
    }

    override fun onBackPressed() {
        miniPlayer.visibility = View.VISIBLE
        super.onBackPressed()
    }

    private fun onMiniPlayerClicked(song: Song) {
            val nowPlayingFragment = NowPlayingFragment()
            val argumentsBundle = Bundle().apply {
                putParcelable(NowPlayingFragment.SONG_KEY, song)
            }
            nowPlayingFragment.arguments = argumentsBundle

            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragContainer, nowPlayingFragment, NowPlayingFragment.TAG)
                .addToBackStack(NowPlayingFragment.TAG)
                .commit()

            miniPlayer.visibility = View.GONE
    }

    override fun onSongPlayed(song: Song) {
        updateSong(song)
    }
}
