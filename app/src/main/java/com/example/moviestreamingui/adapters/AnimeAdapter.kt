package com.example.moviestreamingui.adapters

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestreamingui.ui.AnimeDetailActivity
import com.example.moviestreamingui.R
import com.example.moviestreamingui.models.Anime
import kotlinx.android.synthetic.main.item_anime.view.*

class AnimeAdapter(
    private val items:MutableList<Anime>,
    private val mcontext:Activity):
    RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>(){
    class AnimeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_anime,parent,false)
        val viewHolder = AnimeViewHolder(view)

        view.setOnClickListener {
            if(viewHolder.adapterPosition != RecyclerView.NO_POSITION) {
                val nextIntent: Intent = Intent(mcontext, AnimeDetailActivity::class.java).apply {
                    putExtra("title", items[viewHolder.adapterPosition].title)
                    putExtra("thumbnail", items[viewHolder.adapterPosition].thumbnail)
                    putExtra("detailCover",items[viewHolder.adapterPosition].detailCover)
                    putExtra("url",items[viewHolder.adapterPosition].viedeoUrl)
                }
                val option = ActivityOptions.makeSceneTransitionAnimation(mcontext, view.item_anime_img, "sharedName")
                startActivity(mcontext,nextIntent,option.toBundle())
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        with(holder.itemView){
            item_anime_title.text = items[position].title
            item_anime_img.setImageResource(items[position].thumbnail)
        }
    }

    override fun getItemCount() = items.size
}