package edu.washington.hoganc17.dotify

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ericchee.songdataprovider.Song

class SongAdapter(songList: List<Song>) : RecyclerView.Adapter<SongAdapter.SongViewHolder>() {

    private var songList: List<Song> = songList.toList()
    var onSongClickListener: ((title: String, artist: String, albumID: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)
        return SongViewHolder(view)
    }

    override fun getItemCount(): Int = songList.size

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = songList[position]
        holder.bind(song)
    }

    fun change(newSongs: List<Song>) {
        val callback = SongDiffCallback(songList, newSongs)
        val diffResult = DiffUtil.calculateDiff(callback)
        diffResult.dispatchUpdatesTo(this)


        songList = newSongs
    }

    inner class SongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvListSongTitle by lazy { itemView.findViewById<TextView>(R.id.tvListSongTitle) }
        private val tvListArtist by lazy { itemView.findViewById<TextView>(R.id.tvListArtist) }
        private val ivListAlbumArt by lazy { itemView.findViewById<ImageView>(R.id.ivListAlbumArt) }

        fun bind(song: Song) {
            tvListSongTitle.text = song.title
            tvListArtist.text = song.artist
            ivListAlbumArt.setImageResource(song.smallImageID)

            itemView.setOnClickListener {
                onSongClickListener?.invoke(song.title, song.artist, song.largeImageID)
            }
        }
    }
}