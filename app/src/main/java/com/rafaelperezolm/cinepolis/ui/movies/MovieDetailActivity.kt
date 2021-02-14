package com.rafaelperezolm.cinepolis.ui.movies

import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.rafaelperezolm.cinepolis.common.Const
import com.rafaelperezolm.cinepolis.databinding.ActivityMovieDetailBinding
import dagger.hilt.android.AndroidEntryPoint


//Shows the selected movie data
@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    //View Binding to vinculate the xml components to the code
    private lateinit var binding: ActivityMovieDetailBinding
    //Store and manage UI-related data
    private val viewModel: MovieDetailViewModel by viewModels()
    //Exoplayer
    private lateinit var player: SimpleExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Getting the movieId for showing the realted data
        val extrasBundle: Bundle? = intent.extras
        val movieId = extrasBundle?.getInt("movie_id")
        viewModel.start(movieId!!)

        //Starting the observer
        setupObservers()

        //Back button for return
        binding.detailImbBack.setOnClickListener {
            if(player.isPlaying) {
                player.stop()
            }
            super.onBackPressed()
        }

    }

    private fun setupObservers() {
        viewModel.movie.observe(this, Observer {

            var resource: String = ""
            for (i in it.media.indices) {
                if (it.media[i].code == "trailer_mp4") {
                    resource = it.media[i].resource
                }
            }

            player = ExoPlayerFactory.newSimpleInstance(this)
            binding.detailVdvTrailer.player = player
            val dataSourceFactory: DataSource.Factory =
                DefaultDataSourceFactory(this, Util.getUserAgent(this, "Cinepolis"))
            val videoSource: MediaSource =
                ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(
                    Uri.parse("${Const.API_TRAILER_URL}$resource")
                )
            player.prepare(videoSource)
            player.playWhenReady = true

            binding.detailTxvName.text = it.name
            binding.detailTxvRating.text = it.rating
            binding.detailTxvGenre.text = it.genre
            binding.detailTxvLength.text = it.length
            binding.detailTxvSynopsis.text = it.synopsis

        })
    }

    override fun onBackPressed() {
        if(player.isPlaying) {
            player.stop()
        }
        super.onBackPressed()
    }


}