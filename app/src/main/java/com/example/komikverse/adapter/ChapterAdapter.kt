package com.example.komikverse.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.komikverse.ChapterActivity
import com.example.komikverse.R
import com.example.komikverse.databinding.ChapterItemBinding
import com.example.komikverse.databinding.RvItemBinding
import com.example.komikverse.models.Chapter
import com.example.komikverse.models.Comic
import com.example.komikverse.ui.home.HomeFragmentDirections
import com.squareup.picasso.Picasso
import java.io.Serializable

class ChapterAdapter(private val comic: Comic, private val chapterList: List<Chapter>) : RecyclerView.Adapter<ChapterAdapter.ViewHolder>() {

    inner class ViewHolder(binding: ChapterItemBinding) : RecyclerView.ViewHolder(binding.root) {

        var chapterBtn: Button

        init {
            chapterBtn = binding.chapterBtn

            chapterBtn.setOnClickListener {
                var position: Int = adapterPosition
                val context = itemView.context
                Log.d("INFO", "recycleView itemOnClick: $position")
                val intent = Intent(context, ChapterActivity::class.java).apply {
                    putExtra("comic", comic)
                    putExtra("chapter", chapterList[position])
                }
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ChapterAdapter.ViewHolder {
        val v = ChapterItemBinding
            .inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.chapterBtn.text = "Chapter ${chapterList[i].index}"
        chapterList[i].title?.let {
            viewHolder.chapterBtn.text = "${viewHolder.chapterBtn.text} - ${chapterList[i].title}"
        }
        Log.d("RESPONSE", "onBindViewHolder: ${chapterList[i]}")
    }

    override fun getItemCount(): Int {
        return chapterList.size
    }
}