package com.example.moviestreamingui.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviestreamingui.R
import com.example.moviestreamingui.adapters.AnimeAdapter
import com.example.moviestreamingui.adapters.AnimeListAdapter
import com.example.moviestreamingui.adapters.SliderAdapterPager
import com.example.moviestreamingui.models.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private val lstSlide = ArrayList<Slide>(10)
    private val lstAnime_list = ArrayList<AnimeList>(10)
    private val lstAnime1 = ArrayList<Anime>(10)
    private val lstAnime2 = ArrayList<Anime>(10)
    private var listModel:AnimeList_ViewModel? = null
    private var animeModel:Anime_ViewModel? = null
    private lateinit var listadapter:AnimeListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initModel()
        dummy_slide()
        dummy_data()
        slidePageinit()
        animeViewinit()
    }
    private fun initModel(){

        animeModel = application!!.let {
            ViewModelProvider(viewModelStore,ViewModelProvider.AndroidViewModelFactory(it))
                .get(Anime_ViewModel::class.java)
        }
        listModel = application!!.let {
            ViewModelProvider(viewModelStore,ViewModelProvider.AndroidViewModelFactory(it))
                .get(AnimeList_ViewModel::class.java)
        }
    }

    private fun dummy_slide(){
        lstSlide.add(Slide(R.drawable.slide1, "던전에서 만남을 추구하면 안되는 걸까",""))
        lstSlide.add(Slide(R.drawable.slide2, "마기아 레코드\n마법소녀 마도카 마기카 외전",""))
        lstSlide.add(Slide(R.drawable.slide3, "네코파라 2020",""))
    }

    private fun dummy_data(){
        lstAnime1.add(Anime("귀멸의 칼날", R.drawable.anime1,R.drawable.anime11,""))
        lstAnime1.add(Anime("페어리 테일", R.drawable.anime2,R.drawable.anime21,""))
        lstAnime1.add(Anime("Re 제로부터 시작하는 이세계 생활", R.drawable.anime3,R.drawable.anime31,""))
        lstAnime1.add(Anime("카구야님은 고백 받고 싶어", R.drawable.anime4,R.drawable.anime41,""))
        lstAnime1.add(Anime("청춘돼지는 바니걸 선배의 꿈을 꾸지 않는다.", R.drawable.anime5,R.drawable.anime51,""))
        lstAnime1.add(Anime("이 멋진 세계의 축복을!", R.drawable.anime6,R.drawable.anime61,""))

        animeModel!!.let {
            for(i in lstAnime1.indices){
                it.addAnime(lstAnime1[i])
            }
        }

        lstAnime2.add(Anime("귀멸의 칼날", R.drawable.bestanime1,R.drawable.anime11,""))
        lstAnime2.add(Anime("마기아 레코드\n마법소녀 마도카 마기카 외전", R.drawable.bestanime2,R.drawable.bestanime21,""))
        lstAnime2.add(Anime("방패 용사 성공담", R.drawable.bestanime3,R.drawable.bestanime31,""))
        lstAnime2.add(Anime("아픈건 싫으니까 방어력에 올인하려고 합니다", R.drawable.bestanime4,R.drawable.bestanime41,""))
        lstAnime2.add(Anime("원펀맨 2기", R.drawable.bestanime5,R.drawable.bestanime51,""))
        lstAnime2.add(Anime("페이트 그랜드 오더", R.drawable.bestanime6,R.drawable.bestanime61,""))
        animeModel!!.let {
            for(i in lstAnime2.indices){
                it.addAnime(lstAnime2[i])
            }
        }

        lstAnime_list.add(AnimeList("BEST POPULAR ANIMATION",lstAnime1))
        lstAnime_list.add(AnimeList("POPULAR THIS WEEK",lstAnime2))
        listModel!!.let {
            for(i in lstAnime_list.indices){
                it.addAnimeList(lstAnime_list[i])
            }
        }
    }

    private fun slidePageinit(){
        val adapter = SliderAdapterPager(lstSlide,this)
        slider_pager.adapter = adapter
        //set Timer
        val timer:Timer = Timer()
        timer.scheduleAtFixedRate(SliderTimer(),4000,6000)
        indicator.setupWithViewPager(slider_pager,true)
    }

    private fun animeViewinit(){
        listModel!!.let{
            it.animeListLiveData.value!!.let{ it ->
                listadapter = AnimeListAdapter(it,this)
                Rv_list.layoutManager = LinearLayoutManager(this)
                Rv_list.adapter = listadapter
            }
            it.animeListLiveData.observe(this,
                androidx.lifecycle.Observer {
                    listadapter.notifyDataSetChanged()
                })
        }
    }

    inner class SliderTimer:TimerTask(){
        override fun run() {
            this@MainActivity.runOnUiThread {
                if(slider_pager.currentItem<lstSlide.size-1){
                    slider_pager.currentItem = (slider_pager.currentItem)+1
                } else{
                    slider_pager.currentItem = 0
                }
            }
        }
    }
}
