package com.example.komikverse

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class BannerAdapter : RecyclerView.Adapter<BannerAdapter.ViewHolder>() {
    private val comicList: ArrayList<Comic> = arrayListOf(
        Comic(
            id = "6473898cd50f5ef9a9c27ae6",
            title = "Solo Leveling Side Story",
            author = "Dubu",
            desc = "Side story of Solo Leveling manhwa",
            thumbUrl = null
        ),
        Comic(
            id = "6473898cd50f5ef9a9c27ae6",
            title = "Solo Leveling Side Story",
            author = "Dubu",
            desc = "Side story of Solo Leveling manhwa",
            thumbUrl = null
        ),
        Comic(
            id = "6473898cd50f5ef9a9c27ae6",
            title = "Solo Leveling Side Story",
            author = "Dubu",
            desc = "Side story of Solo Leveling manhwa",
            thumbUrl = null
        ),
        Comic(
            id = "6473898cd50f5ef9a9c27ae6",
            title = "Solo Leveling Side Story",
            author = "Dubu",
            desc = "Side story of Solo Leveling manhwa",
            thumbUrl = null
        ),
        Comic(
            id = "6473898cd50f5ef9a9c27ae6",
            title = "Solo Leveling Side Story",
            author = "Dubu",
            desc = "Side story of Solo Leveling manhwa",
            thumbUrl = null
        ),
        Comic(
            id = "6473898cd50f5ef9a9c27ae6",
            title = "Solo Leveling Side Story",
            author = "Dubu",
            desc = "Side story of Solo Leveling manhwa",
            thumbUrl = null
        ),
    )

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvTitle: TextView
        var tvImage: ImageView

        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
            tvImage = itemView.findViewById(R.id.tvImage)

            itemView.setOnClickListener {
                var position: Int = getAdapterPosition()
                val context = itemView.context
                Log.d("INFO", "recycleView itemOnClick: ${position}")
//                val intent = Intent(context, DetailPertanyaan::class.java).apply {
//                    putExtra("NUMBER", position)
//                    putExtra("CODE", itemKode.text)
//                    putExtra("CATEGORY", itemKategori.text)
//                    putExtra("CONTENT", itemIsi.text)
//                }
//                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.rv_item, viewGroup, false)
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