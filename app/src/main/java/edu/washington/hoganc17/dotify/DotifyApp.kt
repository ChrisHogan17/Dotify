package edu.washington.hoganc17.dotify

import android.app.Application
import edu.washington.hoganc17.dotify.manager.ApiManager
import edu.washington.hoganc17.dotify.manager.MusicManager

class DotifyApp: Application() {
    lateinit var musicManager: MusicManager
    lateinit var apiManager: ApiManager

    override fun onCreate() {
        super.onCreate()

        apiManager = ApiManager()
        val retrievedSongs = apiManager.fetchSongs()
        musicManager = MusicManager(retrievedSongs)
    }
}