package com.example.komikverse.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.komikverse.ChapterActivity
import com.example.komikverse.R
import com.example.komikverse.models.Chapter
import com.example.komikverse.models.Comic
import com.squareup.picasso.Picasso
import java.io.Serializable

class ChapterAdapter(private val comic: Comic, private val chapterList: List<Chapter>) : RecyclerView.Adapter<ChapterAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var chapterBtn: Button

        init {
            chapterBtn = itemView.findViewById(R.id.chapterBtn)

            chapterBtn.setOnClickListener {
                var position: Int = adapterPosition
                val context = itemView.context
                Log.d("INFO", "recycleView itemOnClick: $position")
                val intent = Intent(context, ChapterActivity::class.java).apply {
                    putExtra("comic", comic)
                    putExtra("chapter", chapterList[position])

                    if (chapterList.size == position+1) {
                        Log.d("CHAPTER", "nextChapter: not available")
                    } else {
                        putExtra("nextChapter", chapterList[position+1])
                    }
                    if (position-1 == -1) {
                        Log.d("CHAPTER", "prevChapter: not available")
                    } else {
                        putExtra("prevChapter", chapterList[position-1])
                    }
                }
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ChapterAdapter.ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.chapter_item, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.chapterBtn.text = chapterList[i].title
        Log.d("RESPONSE", "onBindViewHolder: ${chapterList[i]}")
    }

    override fun getItemCount(): Int {
        return chapterList.size
    }
}