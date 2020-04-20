package edu.washington.hoganc17.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private var playCount = Random.nextInt(10, 100000)
    private lateinit var tvUser: TextView
    private lateinit var tvPlayCount: TextView
    private lateinit var tvSongTitle: TextView
    private lateinit var tvArtist: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvPlayCount = findViewById(R.id.tvPlayCount)
        tvSongTitle = findViewById(R.id.tvSongTitle)
        tvArtist = findViewById(R.id.tvArtist)

        tvPlayCount = findViewById<TextView>(R.id.tvPlayCount)
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
        tvUser.setTextColor(ContextCompat.getColor(this, R.color.red))
        tvSongTitle.setTextColor(ContextCompat.getColor(this, R.color.red))
        tvArtist.setTextColor(ContextCompat.getColor(this, R.color.red))
        tvPlayCount.setTextColor(ContextCompat.getColor(this, R.color.red))
    }

}