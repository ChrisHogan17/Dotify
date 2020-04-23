package edu.washington.hoganc17.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import com.ericchee.songdataprovider.Song
import kotlinx.android.synthetic.main.activity_song_player.*
import kotlin.random.Random


class SongPlayerActivity : AppCompatActivity() {

    companion object {
        const val SONG_KEY = "SONG_KEY"
    }

    private var playCount = Random.nextInt(10, 100000)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_player)

        val playsString = "$playCount plays"
        tvPlayCount.text = playsString

        val ivAlbum = findViewById<ImageView>(R.id.ivAlbum)

        ivAlbum.setOnLongClickListener { ivAlbum: View ->
            changeColors()
            true
        }

        val currSong: Song? = intent.getParcelableExtra(SONG_KEY)
        tvSongTitle.text = currSong?.title
        tvArtist.text = currSong?.artist
        if (currSong != null) {
            ivAlbum.setImageResource(currSong.largeImageID)
        }
    }

    fun playTrack(view: View) {
        playCount++
        val tvPlayCount = findViewById<TextView>(R.id.tvPlayCount)
        val playsString = "$playCount plays"
        tvPlayCount.text = playsString
    }

    fun skipNextTrack(view: View) {
        Toast.makeText(this, "Skipping to next track", Toast.LENGTH_SHORT).show()
    }

    fun skipPrevTrack(view: View) {
        Toast.makeText(this, "Skipping to previous track", Toast.LENGTH_SHORT).show()
    }

    private fun changeColors() {
        tvSongTitle.setTextColor(ContextCompat.getColor(this, R.color.red))
        tvArtist.setTextColor(ContextCompat.getColor(this, R.color.red))
        tvPlayCount.setTextColor(ContextCompat.getColor(this, R.color.red))
    }

}