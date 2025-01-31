package com.example.komikverse.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.komikverse.R
import com.example.komikverse.databinding.ComicItemBinding
import com.example.komikverse.databinding.ImageItemBinding
import com.example.komikverse.models.Chapter
import com.example.komikverse.models.Comic
import com.example.komikverse.models.Image
import com.squareup.picasso.Picasso

class ImageAdapter(
    private val comic: Comic,
    private val chapter: Chapter,
    private val imageList: List<Image>,
    ) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    inner class ViewHolder(binding: ImageItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var context: Context = itemView.context
        var tvImage: ImageView
        init {
            tvImage = binding.tvImage
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ImageAdapter.ViewHolder {
        val v = ImageItemBinding
            .inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ImageAdapter.ViewHolder, i: Int) {
        Log.d("RESPONSE", "onBindViewHolder: image_url ${imageList[i].image_url}")
        Log.d("RESPONSE", "onBindViewHolder: ${imageList[i]}")
        Picasso.get().load(imageList[i].image_url).placeholder(R.drawable.placeholder).into(viewHolder.tvImage)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}