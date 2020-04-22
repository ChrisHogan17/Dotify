package edu.washington.hoganc17.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_song_player.*
import kotlin.random.Random


class SongPlayerActivity : AppCompatActivity() {

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

    fun changeColors() {
        tvSongTitle.setTextColor(ContextCompat.getColor(this, R.color.red))
        tvArtist.setTextColor(ContextCompat.getColor(this, R.color.red))
        tvPlayCount.setTextColor(ContextCompat.getColor(this, R.color.red))
    }

}