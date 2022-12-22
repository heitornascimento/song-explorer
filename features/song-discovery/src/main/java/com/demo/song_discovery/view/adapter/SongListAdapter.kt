package com.demo.song_discovery.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.demo.song_discovery.databinding.SongItemBinding
import com.demo.song_discovery.domain.model.Song

class SongListAdapter() : ListAdapter<Song, SongListAdapter.SongViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = SongItemBinding.inflate(layoutInflater, parent, false)//check
        return SongViewHolder(view)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) = holder.bind(getItem(position))

    inner class SongViewHolder(private val view: SongItemBinding) :
        RecyclerView.ViewHolder(view.root) {

        fun bind(song: Song) {
            view.trackName.text = song.track
            view.artistName?.text = song.artist
            view.artwork?.load(song.artWorkUrl)
            view.description?.text = song.shortDescription
            view.dateTime?.text = song.releaseDate
        }
    }

}

class DiffCallBack : DiffUtil.ItemCallback<Song>() {
    override fun areItemsTheSame(oldItem: Song, newItem: Song) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Song, newItem: Song) =
        oldItem.track + oldItem.releaseDate == newItem.track + newItem.releaseDate
}

