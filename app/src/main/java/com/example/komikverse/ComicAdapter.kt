package com.example.komikverse

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ComicAdapter : RecyclerView.Adapter<ComicAdapter.ViewHolder>() {
    private val comicList: ArrayList<Comic> = arrayListOf(
        Comic(
            id = "6473898cd50f5ef9a9c27ae6",
            title = "Solo Leveling Side Story",
            author = "Dubu",
            desc = "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
            thumbUrl = null
        ),
        Comic(
            id = "6473898cd50f5ef9a9c27ae6",
            title = "Solo Leveling Side Story",
            author = "Dubu",
            desc = "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
            thumbUrl = null
        ),
        Comic(
            id = "6473898cd50f5ef9a9c27ae6",
            title = "Solo Leveling Side Story",
            author = "Dubu",
            desc = "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
            thumbUrl = null
        ),
        Comic(
            id = "6473898cd50f5ef9a9c27ae6",
            title = "Solo Leveling Side Story",
            author = "Dubu",
            desc = "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
            thumbUrl = null
        ),
        Comic(
            id = "6473898cd50f5ef9a9c27ae6",
            title = "Solo Leveling Side Story",
            author = "Dubu",
            desc = "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
            thumbUrl = null
        ),
        Comic(
            id = "6473898cd50f5ef9a9c27ae6",
            title = "Solo Leveling Side Story",
            author = "Dubu",
            desc = "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
            thumbUrl = null
        ),
    )

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvTitle: TextView
        var tvImage: ImageView
        var tvDesc: TextView

        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
            tvImage = itemView.findViewById(R.id.tvImage)
            tvDesc = itemView.findViewById(R.id.tvDesc)

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
            .inflate(R.layout.comic_item, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.tvTitle.text = comicList.get(i).title
        viewHolder.tvDesc.text = comicList.get(i).desc
        viewHolder.tvImage.setImageResource(R.drawable.solev1)
    }

    override fun getItemCount(): Int {
        return comicList.size
    }
}