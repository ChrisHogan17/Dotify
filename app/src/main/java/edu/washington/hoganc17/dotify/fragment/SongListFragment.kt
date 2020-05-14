package edu.washington.hoganc17.dotify.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import edu.washington.hoganc17.dotify.DotifyApp
import edu.washington.hoganc17.dotify.R
import edu.washington.hoganc17.dotify.manager.MusicManager
import edu.washington.hoganc17.dotify.manager.SongsFetchListener
import edu.washington.hoganc17.dotify.model.OnSongClickListener
import edu.washington.hoganc17.dotify.model.Song
import edu.washington.hoganc17.dotify.model.SongAdapter
import kotlinx.android.synthetic.main.fragment_song_list.*

class SongListFragment: Fragment(), SongsFetchListener {
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
        musicManager.songsFetchListener = this
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

    override fun onSongsFetched(fetchedSongList: List<Song>) {
        songAdapter.change(fetchedSongList)
        rvSongs.scrollToPosition(0)
        songList = fetchedSongList
        musicManager.updateList(fetchedSongList)
    }

    override fun onFetchError() {
        Toast.makeText(context, "There was an error retrieving songs", Toast.LENGTH_SHORT).show()
    }

    fun shuffleList() {
        val newSongList = songList.shuffled()
        songAdapter.change(newSongList)
        rvSongs.scrollToPosition(0)
        songList = newSongList
        musicManager.updateList(newSongList)
    }
}