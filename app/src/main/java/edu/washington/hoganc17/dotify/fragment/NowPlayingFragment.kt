package edu.washington.hoganc17.dotify.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ericchee.songdataprovider.Song

import edu.washington.hoganc17.dotify.R
import kotlinx.android.synthetic.main.fragment_now_playing.*
import kotlin.random.Random


class NowPlayingFragment : Fragment() {

    companion object {
        const val SONG_KEY = "SONG_KEY"
    }

    private var playCount = Random.nextInt(10, 100000)
    private var currSong: Song? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let { args ->
            currSong = args.getParcelable(SONG_KEY)
        }
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

    fun updateSong(song: Song) {
        tvSongTitle.text = song.title
        tvArtist.text = song.artist
        ivAlbum.setImageResource(song.largeImageID)
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
