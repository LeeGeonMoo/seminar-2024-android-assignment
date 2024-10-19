package com.wafflestudio.waffleseminar2024

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.Menu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class UserInformationActivity: AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPagerAdapter: ViewPagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_information)

        // 여기도 3개의 함수를 만들어서 그것만 부르는 것으로.
//        addToolbarOption()
//        addWorkspaceUrl()
//        addGithubLink()
        // 특정 탭이 눌렸을때 안으로 넣어야함.


        // 시작
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)

        viewPagerAdapter = ViewPagerAdapter(this)
        viewPager.adapter = viewPagerAdapter

        // 얘는 어뎁터니까 아마 그냥 자동으로 계속 불릴듯함. 그래서 화면 자유자재로 스크롤 하면 바뀌는거고..

        setupTabLayout()

    }

    private fun setupTabLayout() {
        // TabLayout과 ViewPager2 연결
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "게임"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_tab_game) // 아이콘 설정
                }
                1 -> {
                    tab.text = "앱"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_tab_app_menu) // 아이콘 설정
                }
                2 -> {
                    tab.text = "검색"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_tab_search) // 아이콘 설정
                }
                3 -> {
                    tab.text = "프로필"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_tab_profile) // 아이콘 설정
                }
            }
            // 각 탭의 text, icon 추가.
        }.attach()

        // 탭 선택 리스너 추가
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                //viewPager.currentItem = tab.position // 선택한 탭의 인덱스에 따라 ViewPager2의 현재 항목을 설정. 즉 페이지 넘기기.
                Log.d("TabSelected", "Selected tab: ${tab.position}")
                // 프로필 탭이 선택되었을 때

                if (tab.position == 3) {
                    // 프로필 탭에 대한 함수 호출
                    viewPager.post { // 이거 없으면 클릭했을때는 오류 뜸. 페이지 다 로드하기 전에 idfind해서 그런듯함.
                        addToolbarOption()
                        addWorkspaceUrl()
                        addGithubLink()
                        // 프로필 탭 만들었음.

                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                // 다른 탭에서 선택 해제될 때 필요한 동작을 추가할 수 있음
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                // 이미 선택된 탭이 다시 선택되었을 때 필요한 동작을 추가할 수 있음
            }
            // 얘네 3가지 함수가 무조건 정의가 되어야만 진행이 된대.
        })
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_user_information, menu)
        return true
    } // 메뉴 여는 함수인가보지. 그냥 override하면 된다.
    
    private fun addToolbarOption() {
        val toolbar: Toolbar = findViewById(R.id.toolbarUserInformation)
        setSupportActionBar(toolbar)
        
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "프로필"
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_new_24)
    } // 얘도 첫번째 페이지처럼 툴바를 만들어서 xml 파일 노가다 따로 안해도 되도록..

    private fun addWorkspaceUrl() {
        val slackWorkspaceValueView: TextView = findViewById(R.id.slackWorkspaceValue)
        val workspaceUrl = intent.getStringExtra("WORKSPACE_URL")
        slackWorkspaceValueView.text = workspaceUrl
    } // url 텍스트를 불러오는 함수.

    private fun addGithubLink() { // 깃허브 하이퍼링크 연결하는 함수.
        val textView: TextView = findViewById(R.id.githubValue)
        val text = "hjlim7831"
        val spannableString = SpannableString(text) // spannablestring은 텍스트를 더 이쁘게 꾸밀 수 있는 그런 클래스인가봐.

        // 클릭 가능한 텍스트를 만들기.
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: android.view.View) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/hjlim7831"))
                startActivity(intent)
            }

            override fun updateDrawState(ds: TextPaint) { // 클릭하면 자동으로 같이 실행되는 함수래. 텍스트의 시각적 상태 업데이트
                super.updateDrawState(ds)
                ds.isUnderlineText = false
            }
        }

        spannableString.setSpan(clickableSpan, 0, text.length, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        //spannable을 설정했기에 clickspan같은걸 설정할 수 있는 건가봐. 이제 해당 text에 비로소 clickablespan이 적용됨.

        textView.text = spannableString // 이제 택스트뷰가 곧 spannablestring이 되는.
        textView.movementMethod = LinkMovementMethod.getInstance() // 뭐 클릭을 인식하게 해주는 그런건가..? 잘 이해는 안감;;
        // 텍스트뷰 자체가 클릭을 인식하게 해줘야한대. 텍스트뷰 안에 있는 텍스트는 clickable하게 만들긴 했어도 그 겉은 그러지 못한다는 것임.

    }
}