package com.example.komikverse.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.komikverse.adapter.BannerAdapter
import com.example.komikverse.models.Comic
import com.example.komikverse.adapter.ComicAdapter
import com.example.komikverse.api.ComicService
import com.example.komikverse.api.ServiceBuilder
import com.example.komikverse.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var bannerAdapter: RecyclerView.Adapter<BannerAdapter.ViewHolder>? = null
    private var comicAdapter: RecyclerView.Adapter<ComicAdapter.ViewHolder>? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        val horizontalRv: RecyclerView = binding.horizontalRv
        horizontalRv.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            // set the custom adapter to the RecyclerView
            adapter = BannerAdapter()
        }
        val helper: SnapHelper = LinearSnapHelper()
        helper.attachToRecyclerView(horizontalRv)
        horizontalRv.smoothScrollToPosition(1);

        loadComics(itemView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadComics(itemView: View) {
        //initiate the service
        val loaderComics: LinearLayout = binding.loaderComics
        val verticalRv: RecyclerView = binding.verticalRv

        loaderComics.visibility = View.VISIBLE
        verticalRv.visibility = View.GONE
        val destinationService = ServiceBuilder.buildService(ComicService::class.java)
        val requestCall = destinationService.getComicList()
        //make network call asynchronously
        requestCall.enqueue(object : Callback<List<Comic>> {
            override fun onResponse(call: Call<List<Comic>>, response: Response<List<Comic>>) {
                Log.d("Response", "url: ${response.raw().request.url}")
                Log.d("Response", "onResponse: ${response.body()}")
                if (response.isSuccessful){
                    val comicList = response.body()!!
                    Log.d("Response", "countrylist size : ${comicList.size}")
                    verticalRv.apply {
                        layoutManager = LinearLayoutManager(activity)
                        adapter = ComicAdapter(response.body()!!)
                    }
                    val helper: SnapHelper = LinearSnapHelper()
                    helper.attachToRecyclerView(verticalRv)
                }else{
                    Toast.makeText(activity, "Something went wrong ${response.message()}", Toast.LENGTH_SHORT).show()
                }
                loaderComics.visibility = View.GONE
                verticalRv.visibility = View.VISIBLE
            }
            override fun onFailure(call: Call<List<Comic>>, t: Throwable) {
                loaderComics.visibility = View.GONE
                verticalRv.visibility = View.VISIBLE
                Toast.makeText(activity, "Something went wrong $t", Toast.LENGTH_SHORT).show()
            }
        })
    }
}