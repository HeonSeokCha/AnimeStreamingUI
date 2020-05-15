package com.example.moviestreamingui.adapters

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewpager.widget.PagerAdapter
import com.example.moviestreamingui.R
import com.example.moviestreamingui.models.Slide
import com.example.moviestreamingui.ui.AnimePlayerActivity
import androidx.core.content.ContextCompat.startActivity
import kotlinx.android.synthetic.main.item_anime.view.*
import kotlinx.android.synthetic.main.slide_item.view.*

class SliderAdapterPager(private val list:ArrayList<Slide>,private val mcontext: Activity): PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater  = LayoutInflater.from(container.context)
        val view = inflater.inflate(R.layout.slide_item,container,false)

        view.slide_title.text = list[position].Title
        view.slide_img.setImageResource(list[position].image)
        container.addView((view))
        view.main_slide_fab.setOnClickListener {
            val nextIntent = Intent(mcontext,AnimePlayerActivity::class.java).apply {
                putExtra("title",list[position].Title)
                putExtra("url",list[position].url)
            }
            val option = ActivityOptions.makeSceneTransitionAnimation(mcontext, view.main_slide_fab,"")
            startActivity(mcontext,nextIntent,option.toBundle())
        }
        return view
    }
    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View?)
    }
    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun getCount(): Int {
        return list.size
    }

}