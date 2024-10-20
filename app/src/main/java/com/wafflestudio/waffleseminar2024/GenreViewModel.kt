package com.wafflestudio.waffleseminar2024

import User
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import userList

class GenreViewModel : ViewModel() {
    private val _genres = MutableLiveData<List<User>>() // genres 데이터 리스트를 만들었다.
    val genres: LiveData<List<User>> get() = _genres

    init {
        _genres.value =  userList // userList라는 미리 만들어놓은 json parsing 된 데이터로 초기화!
    }
}
