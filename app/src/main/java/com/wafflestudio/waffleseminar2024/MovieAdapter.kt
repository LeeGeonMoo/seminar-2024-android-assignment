package com.wafflestudio.waffleseminar2024

import MovieData
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil3.load

// recyclerview와 movieviewmodel을 연결하는 adapter 클래스를 정의해준다!

class MovieAdapter(private val movies: List<MovieData>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.movieTitle) // xml과 연결하는 부분. 리사이클러뷰의 한 단위.
        val posterImageView: ImageView = itemView.findViewById(R.id.moviePoster)

        fun bind(movie: MovieData) {
            val posterUrl = "https://image.tmdb.org/t/p/w185${movie.poster_path}" // URL 생성
            posterImageView.load(posterUrl) // Coil로 이미지 로딩
        }

//        fun bind(movie: Movie) {
//            titleTextView.text = movie.title
//            // 포스터 이미지를 로드하는 방법 (예: Glide 사용)
//            Glide.with(itemView.context)
//                .load(movie.posterUrl)
//                .into(posterImageView)
//        }
        // 아직 포스터 구현은 x
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false) // item_movie라는 xml과 연결. 리사이클러뷰의 한 단위가 된다.
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.titleTextView.text = movies[position].title // getItem은 filtering에서 정의한다.
        // movie 제목 연결하는.
        holder.bind(movies[position]) // 포스터 데이터 할당
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}
