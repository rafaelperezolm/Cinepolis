package com.rafaelperezolm.cinepolis.ui.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.rafaelperezolm.cinepolis.databinding.FragmentMoviesBinding
import com.rafaelperezolm.cinepolis.utils.Resource
import com.rafaelperezolm.cinepolis.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

//Get and show the list of movies
@AndroidEntryPoint
class MoviesFragment : Fragment(), MoviesAdapter.MovieItemListener {

    //View Binding to vinculate the xml components to the code
    private var binding: FragmentMoviesBinding by autoCleared()
    //Store and manage UI-related data
    private val viewModel: MoviesViewModel by viewModels()
    private lateinit var adapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Setting the recycler view
        setupRecyclerView()
        //Starting the observer
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = MoviesAdapter(this)
        binding.charactersRv.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.charactersRv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    if (!it.data.isNullOrEmpty()) {
                        binding.progressBar.visibility = View.INVISIBLE
                        adapter.setItems(ArrayList(it.data))
                    }
                }

                Resource.Status.ERROR -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }

                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

            }
        })
    }

    //When movie item is selected the app shows the related data
    override fun onClickedMovie(movieId: Int) {
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra("movie_id", movieId)
        startActivity(intent)
    }

}