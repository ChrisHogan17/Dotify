package edu.washington.hoganc17.dotify.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.squareup.picasso.Picasso
import edu.washington.hoganc17.dotify.DotifyApp

import edu.washington.hoganc17.dotify.R
import edu.washington.hoganc17.dotify.model.Song
import kotlinx.android.synthetic.main.fragment_now_playing.*
import kotlin.random.Random


class NowPlayingFragment : Fragment() {

    companion object {
        val TAG: String = NowPlayingFragment::class.java.simpleName
        const val OUT_PLAY_COUNT = "OUT_PLAY_COUNT"
    }

    private var playCount = Random.nextInt(10, 100000)
    private var currSong: Song? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            with(savedInstanceState) {
                val savedCount = getInt(OUT_PLAY_COUNT)
                playCount = savedCount
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val musicManager = (context.applicationContext as DotifyApp).musicManager
        currSong = musicManager.currSong
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_now_playing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val playsString = "$playCount plays"
        tvPlayCount.text = playsString

        ibPlay.setOnClickListener {
            playTrack()
        }

        ibNext.setOnClickListener {
            skipTrack("Next")
        }

        ibPrev.setOnClickListener {
            skipTrack("Prev")
        }

        currSong?.let { song ->
            updateSong(song)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(OUT_PLAY_COUNT, playCount)
        super.onSaveInstanceState(outState)
    }

    private fun updateSong(song: Song) {
        tvSongTitle.text = song.title
        tvArtist.text = song.artist
        Picasso.get().load(song.largeImageURL).placeholder(R.drawable.album_placeholder).fit().noFade().into(ivAlbum)
    }

    private fun playTrack() {
        playCount++
        val playsString = "$playCount plays"
        tvPlayCount.text = playsString
    }

    private fun skipTrack(type: String) {
        Toast.makeText(context, "Skipping to $type track", Toast.LENGTH_SHORT).show()
    }

}
