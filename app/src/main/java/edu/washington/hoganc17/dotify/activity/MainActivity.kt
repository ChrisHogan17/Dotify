package edu.washington.hoganc17.dotify.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import edu.washington.hoganc17.dotify.R
import edu.washington.hoganc17.dotify.fragment.NowPlayingFragment
import edu.washington.hoganc17.dotify.fragment.SongListFragment
import edu.washington.hoganc17.dotify.model.OnSongClickListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnSongClickListener {

    private var currSong: Song? = null
    private lateinit var masterSongList: ArrayList<Song>

    companion object {
        private const val OUT_CURR_SONG = "OUT_CURR_SONG"
        private const val OUT_SONG_LIST = "OUT_SONG_LIST"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            with(savedInstanceState) {
                val song = getParcelable<Song?>(OUT_CURR_SONG)
                song?.let {
                    updateSong(it)
                }
                val songList = getParcelableArrayList<Song>(OUT_SONG_LIST)
                songList?.let {
                    masterSongList = it
                }
            }
        } else {
            masterSongList = SongDataProvider.getAllSongs() as ArrayList<Song>
        }

        miniPlayer.visibility = View.GONE

        lateinit var songListFragment: SongListFragment
        if (supportFragmentManager.backStackEntryCount == 0) {
            songListFragment = SongListFragment()
            val argumentsBundle = Bundle().apply {
                putParcelableArrayList(SongListFragment.ARG_SONG_LIST, masterSongList)
            }
            songListFragment.arguments = argumentsBundle


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
            currSong?.apply {
                onMiniPlayerClicked(this)
            }
        }

        btnShuffle.setOnClickListener {
            masterSongList = songListFragment.shuffleList()
        }
    }

    override fun onSongClicked(song: Song) {
        updateSong(song)
    }

    private fun updateSong(song: Song) {
        tvMiniPlayer.text = getString(R.string.miniPlayerText).format(song.title, song.artist)
        currSong = song
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(OUT_CURR_SONG, currSong)
        outState.putParcelableArrayList(OUT_SONG_LIST, masterSongList)
        super.onSaveInstanceState(outState)
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
}
