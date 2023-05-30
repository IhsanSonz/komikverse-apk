package com.example.komikverse.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.komikverse.BannerAdapter
import com.example.komikverse.ComicAdapter
import com.example.komikverse.R
import com.example.komikverse.databinding.FragmentHomeBinding


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
        val horizontalRv: RecyclerView = itemView.findViewById(R.id.horizontalRv)
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

        val verticalRv: RecyclerView = itemView.findViewById(R.id.verticalRv)
        verticalRv.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = ComicAdapter()
        }
        val helper2: SnapHelper = LinearSnapHelper()
        helper2.attachToRecyclerView(verticalRv)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}