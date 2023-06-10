package com.example.komikverse

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.komikverse.adapter.ChapterAdapter
import com.example.komikverse.api.ComicService
import com.example.komikverse.api.ServiceBuilder
import com.example.komikverse.databinding.ActivityChapterBinding
import com.example.komikverse.databinding.ActivityComicBinding
import com.example.komikverse.models.Chapter
import com.example.komikverse.models.Comic
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ComicActivity : AppCompatActivity() {
    private lateinit var comic: Comic;

    private lateinit var binding: ActivityComicBinding
    private val args: ComicActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComicBinding.inflate(layoutInflater) //initializing the binding class
        setContentView(binding.root) // we now set the contentview as the binding.root

        supportActionBar?.setDisplayHomeAsUpEnabled(true) //show back button

        comic = args.comic
        Log.d("RESPONSE", "onCreate: $comic")

        val tvTitle: TextView = binding.tvTitle
        tvTitle.text = comic.title
        val tvAuthor: TextView = binding.tvAuthor
        tvAuthor.text = comic.author
        val tvDesc: TextView = binding.tvDesc
        tvDesc.text = comic.desc
        val tvImage: ImageView = binding.tvImage
        Picasso.get().load(comic.thumb_url).into(tvImage)
        loadChapters()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun loadChapters() {
        //initiate the service
        val loader: LinearLayout = binding.loader
        val verticalRv: RecyclerView = binding.verticalRv
        val tvChapter: TextView = binding.tvChapter

        loader.visibility = View.VISIBLE
        verticalRv.visibility = View.GONE
        val destinationService = ServiceBuilder.buildService(ComicService::class.java)
        val requestCall = destinationService.getChapterList(comic._id)
        //make network call asynchronously
        requestCall.enqueue(object : Callback<List<Chapter>> {
            override fun onResponse(call: Call<List<Chapter>>, response: Response<List<Chapter>>) {
                Log.d("Response", "url: ${response.raw().request.url}")
                Log.d("Response", "onResponse: ${response.body()}")
                if (response.isSuccessful){
                    val chapterList = response.body()!!
                    Log.d("Response", "chapterList size : ${chapterList.size}")
                    tvChapter.text = "${chapterList.size} Chapters"
                    verticalRv.apply {
                        layoutManager = LinearLayoutManager(this@ComicActivity)
                        adapter = ChapterAdapter(comic, response.body()!!)
                    }
                    val helper: SnapHelper = LinearSnapHelper()
                    helper.attachToRecyclerView(verticalRv)
                }else{
                    Toast.makeText(this@ComicActivity, "Something went wrong ${response.message()}", Toast.LENGTH_SHORT).show()
                }
                loader.visibility = View.GONE
                verticalRv.visibility = View.VISIBLE
            }
            override fun onFailure(call: Call<List<Chapter>>, t: Throwable) {
                loader.visibility = View.GONE
                verticalRv.visibility = View.VISIBLE
                Toast.makeText(this@ComicActivity, "Something went wrong $t", Toast.LENGTH_SHORT).show()
            }
        })
    }
}