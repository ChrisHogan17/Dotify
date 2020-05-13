package edu.washington.hoganc17.dotify.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ericchee.songdataprovider.Song
import edu.washington.hoganc17.dotify.DotifyApp
import edu.washington.hoganc17.dotify.R
import edu.washington.hoganc17.dotify.manager.MusicManager
import edu.washington.hoganc17.dotify.model.OnSongClickListener
import edu.washington.hoganc17.dotify.model.SongAdapter
import kotlinx.android.synthetic.main.fragment_song_list.*

class SongListFragment: Fragment() {
    private lateinit var songAdapter: SongAdapter
    private lateinit var songList: List<Song>
    private lateinit var musicManager: MusicManager

    private var onSongClickListener: OnSongClickListener? = null

    companion object {
        val TAG: String = SongListFragment::class.java.simpleName
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        musicManager = (context.applicationContext as DotifyApp).musicManager
        songList = musicManager.listOfSongs


        if (context is OnSongClickListener) {
            onSongClickListener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_song_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        songAdapter = SongAdapter(songList)
        songAdapter.onSongClickListener = { song ->
            onSongClickListener?.onSongClicked(song)
        }

        rvSongs.adapter = songAdapter
    }

    fun shuffleList() {
        val newSongList = songList.shuffled()
        songAdapter.change(newSongList)
        rvSongs.scrollToPosition(0)
        songList = newSongList
        musicManager.updateList(newSongList)
    }
}