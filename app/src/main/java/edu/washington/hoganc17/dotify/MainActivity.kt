package edu.washington.hoganc17.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private var playCount = Random.nextInt(10, 100000)
    private lateinit var tvUser: TextView
    private lateinit var etUser: EditText
    private lateinit var btnChangeUser: Button
    private lateinit var btnApply: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvUser = findViewById(R.id.tvUser)
        etUser = findViewById(R.id.etUser)
        btnChangeUser = findViewById(R.id.btnChangeUser)
        btnApply = findViewById(R.id.btnApply)

        val tvPlayCount = findViewById<TextView>(R.id.tvPlayCount)
        val playsString = "$playCount plays"
        tvPlayCount.text = playsString
    }

    fun changeUser(view: View) {
        tvUser.visibility = View.INVISIBLE
        btnChangeUser.visibility = View.INVISIBLE
        etUser.visibility = View.VISIBLE
        btnApply.visibility = View.VISIBLE
    }

    fun applyChanges(view: View) {
        val newName = etUser.text.toString()
        if (newName != "") {
            tvUser.text = newName
            tvUser.visibility = View.VISIBLE
            btnChangeUser.visibility = View.VISIBLE
            etUser.visibility = View.INVISIBLE
            btnApply.visibility = View.INVISIBLE
        } else {
            Toast.makeText(this, "Username cannot be empty", Toast.LENGTH_SHORT).show()
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

}