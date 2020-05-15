package com.example.moviestreamingui.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.moviestreamingui.R
import kotlinx.android.synthetic.main.activity_anime_detail.*

class AnimeDetailActivity : AppCompatActivity() {
    private var AnimeUrl:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anime_detail)
        init()
        detail_Anime_fab.setOnClickListener {
            val nextIntent = Intent(this,AnimePlayerActivity::class.java).apply {
                putExtra("url",AnimeUrl)
            }
            startActivity(nextIntent)
        }
    }
    private fun init(){
        if(intent.hasExtra("title")){
            detailAnime_title.text = intent.getStringExtra("title")
            Glide.with(this).load(intent.extras!!.getInt("thumbnail")).into(detailAnime_img)
            Glide.with(this).load(intent.extras!!.getInt("detailCover")).into(detailAnime_cover)
            AnimeUrl = intent.getStringExtra("url")
            detailAnime_cover.animation = AnimationUtils.loadAnimation(this,R.anim.scale_anim)
            detail_Anime_fab.animation = AnimationUtils.loadAnimation(this,R.anim.scale_anim)
        } else{
            Toast.makeText(this,"No Data!",Toast.LENGTH_LONG).show()
        }
    }

    override fun onBackPressed() {
        supportFinishAfterTransition()
        super.onBackPressed()
    }
}
