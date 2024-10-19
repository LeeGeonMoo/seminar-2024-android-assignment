package com.wafflestudio.waffleseminar2024

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.view.View


class ViewPagerAdapter(activity: AppCompatActivity) : RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {

    val tabTitles = arrayOf("게임", "앱", "검색", "프로필") // 4개 메뉴 탭을 담은 어레이를.. 지금은 안쓰이고 있다 ;;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        // 빈 화면과 ConstraintLayout을 위한 ViewHolder 생성
        val view = when (viewType) {
            0 -> layoutInflater.inflate(R.layout.tab_game, parent, false)
            1 -> layoutInflater.inflate(R.layout.tab_app, parent, false)
            2 -> layoutInflater.inflate(R.layout.tab_search, parent, false)
            3 -> layoutInflater.inflate(R.layout.tab_profile, parent, false)
            else -> throw IllegalArgumentException("Invalid ViewType")
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // 필요한 경우, 여기에서 데이터를 바인딩할 수 있어.
    }

    override fun getItemCount(): Int {
        return tabTitles.size
    }

    override fun getItemViewType(position: Int): Int {
        return position // position에 따라 ViewType 반환
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // 각 탭에 필요한 뷰를 초기화할 수 있어.
    }
}
