package edu.washington.hoganc17.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private var playCount = Random.nextInt(10, 100000)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvPlayCount = findViewById<TextView>(R.id.tvPlayCount)
        val playsString = "$playCount plays"
        tvPlayCount.text = playsString
    }

    fun changeUser(view: View) {
        val tvUser = findViewById<TextView>(R.id.tvUser)

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

}