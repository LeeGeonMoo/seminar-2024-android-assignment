package com.wafflestudio.waffleseminar2024


import User
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import userList


class GenreAdapter(private val genres: List<User>) : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    class GenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val genreName: TextView = itemView.findViewById(R.id.genreName) // genreName이라는 변수를 설정.
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_genre, parent, false)
        return GenreViewHolder(view)
    } // 새로운 뷰홀더를 생성한다. 즉, 단위 한칸을 계속 생성한다는 거임. 생성된 뷰홀더는 아래에서 바인딩으로 들어간다. --> 리사이클러뷰에서 필요한 어댑터.

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.genreName.text = genres[position].name
    } // 단위 한칸에다가 text, 즉 데이터를 바인딩 한다. 이 경우에는 각종 장르들의 제목을 바인딩 하는 것.

    override fun getItemCount(): Int {
        return genres.size
    }
}
