package com.example.moviestreamingui.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AnimeList_ViewModel:ViewModel(){
    private val animes:MutableList<AnimeList> = mutableListOf()
    val animeListLiveData:MutableLiveData<MutableList<AnimeList>> by lazy{
        MutableLiveData<MutableList<AnimeList>>().apply{
            value = animes
        }
    }
    fun addAnimeList(data:AnimeList){
        val tempList = animeListLiveData.value
        tempList!!.add(data)
        animeListLiveData.value = tempList
    }
}
