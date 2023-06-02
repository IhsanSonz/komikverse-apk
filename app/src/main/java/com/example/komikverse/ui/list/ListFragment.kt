package com.example.komikverse.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.komikverse.R
import com.example.komikverse.adapter.BannerAdapter
import com.example.komikverse.adapter.ComicAdapter
import com.example.komikverse.adapter.ComicListAdapter
import com.example.komikverse.api.ComicService
import com.example.komikverse.api.ServiceBuilder
import com.example.komikverse.databinding.FragmentListBinding
import com.example.komikverse.models.Comic
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private var ComicListAdapter: RecyclerView.Adapter<ComicListAdapter.ViewHolder>? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val listViewModel =
            ViewModelProvider(this).get(ListViewModel::class.java)

        _binding = FragmentListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        loadComics(itemView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadComics(itemView: View) {
        //initiate the service
        val loaderComics: LinearLayout = itemView.findViewById(R.id.loaderComics)
        val verticalRv: RecyclerView = itemView.findViewById(R.id.verticalRv)

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