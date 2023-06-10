package com.example.komikverse.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.komikverse.R
import com.example.komikverse.databinding.ComicItemBinding
import com.example.komikverse.databinding.RvItemBinding
import com.example.komikverse.models.Comic


class BannerAdapter : RecyclerView.Adapter<BannerAdapter.ViewHolder>() {
    private val comicList: ArrayList<Comic> = arrayListOf(
        Comic(
            _id = "6473898cd50f5ef9a9c27ae6",
            title = "Solo Leveling Side Story",
            author = "Dubu",
            desc = "Side story of Solo Leveling manhwa",
            thumb_url = "https://i.ibb.co/Xt72xHm/solo-Leveling-Cover02.png",
        ),
        Comic(
            _id = "6473898cd50f5ef9a9c27ae6",
            title = "Solo Leveling Side Story",
            author = "Dubu",
            desc = "Side story of Solo Leveling manhwa",
            thumb_url = "https://i.ibb.co/Xt72xHm/solo-Leveling-Cover02.png",
        ),
        Comic(
            _id = "6473898cd50f5ef9a9c27ae6",
            title = "Solo Leveling Side Story",
            author = "Dubu",
            desc = "Side story of Solo Leveling manhwa",
            thumb_url = "https://i.ibb.co/Xt72xHm/solo-Leveling-Cover02.png",
        ),
        Comic(
            _id = "6473898cd50f5ef9a9c27ae6",
            title = "Solo Leveling Side Story",
            author = "Dubu",
            desc = "Side story of Solo Leveling manhwa",
            thumb_url = "https://i.ibb.co/Xt72xHm/solo-Leveling-Cover02.png",
        ),
        Comic(
            _id = "6473898cd50f5ef9a9c27ae6",
            title = "Solo Leveling Side Story",
            author = "Dubu",
            desc = "Side story of Solo Leveling manhwa",
            thumb_url = "https://i.ibb.co/Xt72xHm/solo-Leveling-Cover02.png",
        ),
    )
    inner class ViewHolder(binding: RvItemBinding) : RecyclerView.ViewHolder(binding.root) {

        var tvTitle: TextView
        var tvImage: ImageView

        init {
            tvTitle = binding.tvTitle
            tvImage = binding.tvImage

            itemView.setOnClickListener {
                var position: Int = getAdapterPosition()
                Log.d("INFO", "recycleView itemOnClick: ${comicList[position]._id} [${position}]")
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = RvItemBinding
            .inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.tvTitle.text = comicList.get(i).title
        viewHolder.tvImage.setImageResource(R.drawable.solev1)
    }

    override fun getItemCount(): Int {
        return comicList.size
    }
}