package com.example.komikverse.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.komikverse.ChapterActivity
import com.example.komikverse.ComicActivity
import com.example.komikverse.R
import com.example.komikverse.models.Comic
import com.squareup.picasso.Picasso

class ComicListAdapter(private val comicList: List<Comic>) : RecyclerView.Adapter<ComicListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvTitle: TextView
        var tvImage: ImageView
        var tvDesc: TextView

        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
            tvImage = itemView.findViewById(R.id.tvImage)
            tvDesc = itemView.findViewById(R.id.tvDesc)

            itemView.setOnClickListener {
                var position: Int = adapterPosition
                val context = itemView.context
                Log.d("INFO", "recycleView itemOnClick: $position")
                val intent = Intent(context, ComicActivity::class.java).apply {
                    putExtra("comic", comicList[position])
                }
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ComicListAdapter.ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.comic_item, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ComicListAdapter.ViewHolder, i: Int) {
        viewHolder.tvTitle.text = comicList[i].title
        viewHolder.tvDesc.text = comicList[i].desc
        Log.d("RESPONSE", "onBindViewHolder: ${comicList[i].thumb_url}")
        Picasso.get().load(comicList[i].thumb_url).into(viewHolder.tvImage);
    }

    override fun getItemCount(): Int {
        return comicList.size
    }
}