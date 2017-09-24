package com.harismawan.slapp.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment

import com.harismawan.slapp.R
import com.harismawan.slapp.config.Constant
import kotlinx.android.synthetic.main.activity_video.*

class VideoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        initPlayer()
    }

    private fun initPlayer() {
        val youTubePlayerSupportFragment = YouTubePlayerSupportFragment.newInstance()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.youtubeLayout, youTubePlayerSupportFragment).commit()

        youTubePlayerSupportFragment.initialize(Constant.apiKey, object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(provider: YouTubePlayer.Provider, player: YouTubePlayer, wasRestored: Boolean) {
                if (!wasRestored) {
                    player.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL)
                    player.loadVideo(intent.getStringExtra(Constant.extraLink))
                    player.play()
                }
            }

            override fun onInitializationFailure(provider: YouTubePlayer.Provider, error: YouTubeInitializationResult) {
                val errorMessage = error.toString()
                Snackbar.make(container, errorMessage, Snackbar.LENGTH_LONG).show()
                Log.d("errorMessage:", errorMessage)
            }
        })
    }
}
