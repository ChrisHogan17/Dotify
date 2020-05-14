package edu.washington.hoganc17.dotify.manager

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import edu.washington.hoganc17.dotify.model.Song
import edu.washington.hoganc17.dotify.model.SongPackage

class ApiManager(context: Context) {
    private val queue = Volley.newRequestQueue(context)

    fun fetchSongs(onSongsReady: (List<Song>) -> Unit, onSongsFetchError: (() -> Unit)? = null) {
        val songURL = "https://raw.githubusercontent.com/echeeUW/codesnippets/master/musiclibrary.json"
        val stringRequest = StringRequest(Request.Method.GET, songURL,
            { response ->
                //success
                val gson = Gson()
                val retrievedSongs = gson.fromJson(response, SongPackage::class.java)
                onSongsReady(retrievedSongs.songs)
            }, { _ ->
                onSongsFetchError?.invoke()
            }
        )

        queue.add(stringRequest)
    }
}