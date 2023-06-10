package com.example.komikverse.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.komikverse.ComicActivity
import com.example.komikverse.databinding.ComicItemBinding
import com.example.komikverse.models.Comic
import com.example.komikverse.ui.home.HomeFragmentDirections
import com.squareup.picasso.Picasso


class ComicAdapter(private val comicList: List<Comic>) : RecyclerView.Adapter<ComicAdapter.ViewHolder>() {

    inner class ViewHolder(binding: ComicItemBinding) : RecyclerView.ViewHolder(binding.root) {

        var tvTitle: TextView
        var tvImage: ImageView
        var tvDesc: TextView

        init {
            tvTitle = binding.tvTitle
            tvImage = binding.tvImage
            tvDesc = binding.tvDesc

            itemView.setOnClickListener {
                var position: Int = adapterPosition
                val context = itemView.context
                Log.d("INFO", "recycleView itemOnClick: $position")

                val passData = HomeFragmentDirections.actionNavigationHomeToComicActivity(
                    comicList[position]
                )
                it.findNavController().navigate(passData)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = ComicItemBinding
            .inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.tvTitle.text = comicList[i].title
        viewHolder.tvDesc.text = comicList[i].desc
        Log.d("RESPONSE", "onBindViewHolder: ${comicList[i].thumb_url}")
        Picasso.get().load(comicList[i].thumb_url).into(viewHolder.tvImage);
    }

    override fun getItemCount(): Int {
        return comicList.size
    }
}