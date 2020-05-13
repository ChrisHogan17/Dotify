package edu.washington.hoganc17.dotify.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import edu.washington.hoganc17.dotify.DotifyApp
import edu.washington.hoganc17.dotify.R
import edu.washington.hoganc17.dotify.fragment.NowPlayingFragment
import edu.washington.hoganc17.dotify.fragment.SongListFragment
import edu.washington.hoganc17.dotify.manager.MusicManager
import edu.washington.hoganc17.dotify.manager.SongPlayedListener
import edu.washington.hoganc17.dotify.model.OnSongClickListener
import edu.washington.hoganc17.dotify.model.Song
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnSongClickListener, SongPlayedListener {

    private lateinit var musicManager: MusicManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        musicManager = (application as DotifyApp).musicManager
        musicManager.songPlayedListener = this
        musicManager.currSong?.let {
            updateMiniPlayer(it)
        }

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
            musicManager.currSong?.apply {
                onMiniPlayerClicked()
            }
        }

        btnShuffle.setOnClickListener {
            val frag = supportFragmentManager.findFragmentByTag(SongListFragment.TAG) as? SongListFragment
            frag?.shuffleList()
        }
    }

    override fun onSongClicked(song: Song) {
        musicManager.playSong(song)
    }

    override fun onSongPlayed(song: Song) {
        updateMiniPlayer(song)
    }


    private fun updateMiniPlayer(song: Song) {
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

    private fun onMiniPlayerClicked() {
            val nowPlayingFragment = NowPlayingFragment()

            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragContainer, nowPlayingFragment, NowPlayingFragment.TAG)
                .addToBackStack(NowPlayingFragment.TAG)
                .commit()

            miniPlayer.visibility = View.GONE
    }
}
