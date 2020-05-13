package edu.washington.hoganc17.dotify

import android.app.Application
import edu.washington.hoganc17.dotify.manager.MusicManager

class DotifyApp: Application() {
    lateinit var musicManager: MusicManager

    override fun onCreate() {
        super.onCreate()

        musicManager = MusicManager()
    }
}