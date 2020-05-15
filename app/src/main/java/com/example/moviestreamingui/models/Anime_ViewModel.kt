package com.example.moviestreamingui.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Anime_ViewModel:ViewModel(){
    private val animes:MutableList<Anime> = mutableListOf()
    val animeLiveData:MutableLiveData<MutableList<Anime>> by lazy{
        MutableLiveData<MutableList<Anime>>().apply{
            value = animes
        }
    }
    fun addAnime(data:Anime){
        val tempList = animeLiveData.value
        tempList!!.add(data)
        animeLiveData.value = tempList
    }
}
