package com.example.moviestreamingui.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestreamingui.R
import com.example.moviestreamingui.models.AnimeList
import kotlinx.android.synthetic.main.item_anime_list.view.*

class AnimeListAdapter(private val items:MutableList<AnimeList>,
    private val mcontext: Activity):RecyclerView.Adapter<AnimeListAdapter.AnimeListViewHolder>(){
    class AnimeListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_anime_list,parent,false)
        return AnimeListViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimeListViewHolder, position: Int) {
        val adapter:AnimeAdapter = AnimeAdapter(items[position].animList,mcontext)
        with(holder.itemView){
            Rv_animes.setHasFixedSize(true)
            Rv_animes.layoutManager = LinearLayoutManager(mcontext,
            LinearLayoutManager.HORIZONTAL,false)
            Rv_animes.adapter = adapter
            txt_Anime_list_Title.text  = items[position].title
        }
    }

    override fun getItemCount()  = items.size
}
