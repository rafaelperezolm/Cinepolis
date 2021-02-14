package com.rafaelperezolm.cinepolis.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rafaelperezolm.cinepolis.common.Const
import com.rafaelperezolm.cinepolis.data.entities.Movie
import com.rafaelperezolm.cinepolis.data.entities.Movy
import com.rafaelperezolm.cinepolis.databinding.ItemMovieBinding

class MoviesAdapter(private val listener: MovieItemListener) : RecyclerView.Adapter<MovieViewHolder>() {

    interface MovieItemListener {
        fun onClickedMovie(movieId: Int)
    }

    private val items = ArrayList<Movy>()

    fun setItems(items: ArrayList<Movy>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding: ItemMovieBinding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

}

class MovieViewHolder(private val itemBinding: ItemMovieBinding, private val listener: MoviesAdapter.MovieItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var movie: Movy
    private lateinit var route: Movie

    init {
        itemBinding.root.setOnClickListener(this)
    }

    fun bind(item: Movy) {
        this.movie = item
        itemBinding.movieTxvName.text = item.name
        itemBinding.movieTxvGenre.text = item.genre

        //Finds the poster media resource
        var resource: String = ""
        for(i in item.media.indices) {
            if(item.media[i].code == "poster") {
                resource = item.media[i].resource
            }
        }

        //Shows the poster
        Glide.with(itemBinding.root)
            .load("${Const.API_POSTER_URL}$resource")
            .into(itemBinding.movieImgPoster)
    }

    override fun onClick(v: View?) {
        listener.onClickedMovie(movie.id)
    }
}
