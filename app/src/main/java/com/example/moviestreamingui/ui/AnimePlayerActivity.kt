package com.example.moviestreamingui.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import com.example.moviestreamingui.R
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.activity_anime_player.*

class AnimePlayerActivity : AppCompatActivity() {
    private var exoPlayer: SimpleExoPlayer? = null
    private var playWhenReady = true
    private var currentwindow = 0
    private var playbackPosition = 0L
    private var AnimeUrl:String = "https://www.w3schools.com/html/mov_bbb.mp4"

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
        setContentView(R.layout.activity_anime_player)
        setFullScreen()
        init()
        initExo(AnimeUrl)
    }
    private fun setFullScreen(){
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
    private fun init(){
        if(intent.hasExtra("url")){
            if(intent.getStringExtra("url")!=""){
                AnimeUrl = "https://www.w3schools.com/html/mov_bbb.mp4"
            }else{
                onBackPressed()
                Toast.makeText(this,"NoData",Toast.LENGTH_SHORT).show()
            }
        } else{
            onBackPressed()
            Toast.makeText(this,"NoData",Toast.LENGTH_SHORT).show()
        }
    }
    private fun initExo(url:String){
        if(exoPlayer == null){
            exoPlayer = ExoPlayerFactory.newSimpleInstance(this)
            anime_exoplyer!!.player = exoPlayer
        }
        fun buildMediaSource(uri:Uri):MediaSource{
            var userAgent:String = Util.getUserAgent(this,"appname")
            return ExtractorMediaSource.Factory(DefaultDataSourceFactory(this,userAgent)).createMediaSource(uri)
        }
        var mediasource:MediaSource = buildMediaSource(Uri.parse(url))
        exoPlayer!!.prepare(mediasource,true,false)
        exoPlayer!!.playWhenReady.and(playWhenReady)
    }

    override fun onStop() {
        super.onStop()
        exoPlayer!!.release()
    }
}

