package com.example.komikverse

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.komikverse.adapter.ImageAdapter
import com.example.komikverse.api.ComicService
import com.example.komikverse.api.ServiceBuilder
import com.example.komikverse.databinding.ActivityChapterBinding
import com.example.komikverse.models.Chapter
import com.example.komikverse.models.Comic
import com.example.komikverse.models.Image
import com.example.komikverse.models.PrevNext
import com.google.android.material.button.MaterialButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChapterActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var spinner: Spinner
    private lateinit var comic: Comic;
    private lateinit var chapter: Chapter;

    private lateinit var binding: ActivityChapterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChapterBinding.inflate(layoutInflater) //initializing the binding class
        setContentView(binding.root) // we now set the contentview as the binding.root

        supportActionBar?.setDisplayHomeAsUpEnabled(true) //show back button

        comic = intent.getSerializableExtra("comic") as Comic
        Log.d("CHAPTER", "onCreate: comic = $comic")
        chapter = intent.getSerializableExtra("chapter") as Chapter
        Log.d("CHAPTER", "onCreate: chapter = $chapter")

        spinner = binding.langSpinner
        ArrayAdapter.createFromResource(
            this@ChapterActivity,
            R.array.lang_array,
            android.R.layout.simple_spinner_item,
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener = this

        loadPrevNext()


        loadImages()
    }

    private fun loadImages() {
        //initiate the service
        val loader: LinearLayout = binding.loader
        val verticalRv: RecyclerView = binding.verticalRv

        loader.visibility = View.VISIBLE
        verticalRv.visibility = View.GONE
        val destinationService = ServiceBuilder.buildService(ComicService::class.java)
        val lang = getResources().getStringArray(R.array.lang_array_values)[spinner.getSelectedItemPosition()];
        val requestCall = destinationService.getImageList(comic._id, chapter._id, lang)
        //make network call asynchronously
        requestCall.enqueue(object : Callback<List<Image>> {
            override fun onResponse(call: Call<List<Image>>, response: Response<List<Image>>) {
                Log.d("Response", "url: ${response.raw().request.url}")
                Log.d("Response", "onResponse: ${response.body()}")
                if (response.isSuccessful){
                    val imageList = response.body()!!
                    Log.d("Response", "imageList size : ${imageList.size}")
                    verticalRv.apply {
                        layoutManager = LinearLayoutManager(this@ChapterActivity)
                        adapter = ImageAdapter(comic, chapter, response.body()!!)
                    }
                    if (imageList.isEmpty()) {
                        binding.noImage.visibility = View.VISIBLE
                    } else {
                        verticalRv.visibility = View.VISIBLE
                        binding.noImage.visibility = View.GONE
                    }
                }else{
                    Toast.makeText(this@ChapterActivity, "Something went wrong ${response.message()}", Toast.LENGTH_SHORT).show()
                }
                loader.visibility = View.GONE
            }
            override fun onFailure(call: Call<List<Image>>, t: Throwable) {
                loader.visibility = View.GONE
                binding.noImage.visibility = View.VISIBLE
                Toast.makeText(this@ChapterActivity, "Something went wrong $t", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun loadPrevNext() {
        //initiate the service
        val prevBtn: MaterialButton = binding.prevBtn
        val nextBtn: MaterialButton = binding.nextBtn

        prevBtn.visibility = View.GONE
        nextBtn.visibility = View.GONE
        val destinationService = ServiceBuilder.buildService(ComicService::class.java)
        val requestCall = destinationService.getChapterPrevNext(comic._id, chapter._id)
        //make network call asynchronously
        requestCall.enqueue(object : Callback<PrevNext> {
            override fun onResponse(call: Call<PrevNext>, response: Response<PrevNext>) {
                Log.d("Response", "url: ${response.raw().request.url}")
                Log.d("Response", "onResponse: ${response.body()}")
                if (response.isSuccessful){
                    val prevnext = response.body()!!
                    prevnext.prev?.let {
                        prevBtn.visibility = View.VISIBLE
                        prevBtn.setOnClickListener {
                            chapter = prevnext.prev
                            loadImages()
                            loadPrevNext()
                        }
                    }
                    prevnext.next?.let {
                        nextBtn.visibility = View.VISIBLE
                        nextBtn.setOnClickListener {
                            chapter = prevnext.next
                            loadImages()
                            loadPrevNext()
                        }
                    }
                }else{
                    Toast.makeText(this@ChapterActivity, "Something went wrong ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<PrevNext>, t: Throwable) {
                prevBtn.visibility = View.VISIBLE
                nextBtn.visibility = View.VISIBLE
                Toast.makeText(this@ChapterActivity, "Something went wrong $t", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        val position = parent.getItemAtPosition(pos)
        Log.d("INFO", "onItemSelected: $position, $pos, $id")
        loadImages()
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
        Log.d("INFO", "onNothingSelected: nothing selected")
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}