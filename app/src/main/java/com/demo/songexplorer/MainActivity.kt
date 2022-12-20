package com.demo.songexplorer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent()
        intent.setClassName(this, "com.demo.song_discovery.view.SearchSongActivity")
        startActivity(intent)
        finish()
    }
}