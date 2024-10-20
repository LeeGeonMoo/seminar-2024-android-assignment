package com.wafflestudio.waffleseminar2024

import MovieData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import movieListData
import userList

class MovieViewModel : ViewModel(){
    private val _movieList = MutableLiveData<List<MovieData>>() // movie 데이터 리스트를 만들었다.
    val movieList: LiveData<List<MovieData>> get() = _movieList

    init {
        _movieList.value =  movieListData // movieListData라는 미리 만들어놓은 json parsing 된 데이터로 초기화!
    }

    private val _filteredMovieList = MutableLiveData<List<MovieData>>() // 원본을 그대로 놔두려고, filtered value를 정의한다.
    val filteredMovieList: LiveData<List<MovieData>> get() = _filteredMovieList

    fun filterMovies(query: String) { // query가 activity에서 it.toString으로 스트링으로 불린다.
        // 영화 리스트에서 제목이 검색어를 포함하는 영화만 필터링

        // 일단 장르 이름과 id를 매칭
        val matchingGenreIds = if (!query.isNullOrEmpty()) {
            userList.filter { it.name.contains(query, ignoreCase = true) }
                .map { it.id } // map은 [ID]만을 추출해주는 함수
        } else {
            emptyList() // 검색어가 없으면 빈 리스트로 설정
        }
        // 검색어가 없거나, 조건에 맞는 검색어 없으면 (즉 장르가 입력이 안되면) 빈 리스트로 설정됨.


        val filteredList = _movieList.value?.filter { // filter라는건 movieList.value를 for문처럼 다 돌면서 필터링 해주는 것이다. 그래서 it은 MovieData 객체임.
            it.title.contains(query ?: "", ignoreCase = true) // 대소문자 구분 없이 검색
                    || it.genre_ids.any { genreId -> matchingGenreIds.contains(genreId) } // or 처리가 필요함.
        }
        _filteredMovieList.value = filteredList // 필터링된 리스트를 LiveData에 저장. 이제 이걸 관찰하면 된다.
    }

}