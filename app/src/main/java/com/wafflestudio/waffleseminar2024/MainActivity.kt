package com.wafflestudio.waffleseminar2024

import android.content.Intent
import android.os.Bundle
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // 아래에 3개의 새로운 함수를 만들어놓고 그걸 불러오기만 했음.

        addToolbarOption()
        addWorkspaceUrlEditTextOption()
        addContinueButtonOption()

    }

    private fun addToolbarOption() {
        val toolbar: Toolbar = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbar) // 코틀린 기본 함수다.. toolbar라는 애를 '툴바'로 설정하는 역할.
        // 툴바로 설정하면 '툴바' 설정을 쉽게 할 수 있다. '로그인'같은 이름을 쉽게 설정할 수 있다는거.
        // supportActionBar는 툴바를 성정하면 그 안에 들어있는 변수인거임.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "로그인"
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_new_24)
    }

    private fun addWorkspaceUrlEditTextOption() {
        val editText: EditText = findViewById(R.id.workspaceUrl)
        val button: Button = findViewById(R.id.buttonContinue)
        editText.hint = "workspace-url.slack.com"
        editText.addTextChangedListener(WorkspaceUrlTextWatcher(editText, button))
    } // 이건 나도 구현한 내용. 마지막 줄 빼고. workspaceurltextwatcher라는 클래스를 따로 만들어 놓았음.
    // addTextChangedListener는 텍스트가 변경된 것을 감지하면 무언가를 하는 콜백 메서드.
    // 새로 만들어진 클래스는 텍스트 에디터에 뭔가 글자를 쓰면 뒤에 자동으로 slack.com을 추가해주는.

    private fun addContinueButtonOption() {
        val editText: EditText = findViewById(R.id.workspaceUrl)
        val button: Button = findViewById(R.id.buttonContinue)

        button.setOnClickListener {
            val workspaceUrl = editText.text.toString()
            val intent = Intent(this, UserInformationActivity::class.java)
            intent.putExtra("WORKSPACE_URL", workspaceUrl)
            startActivity(intent)
        }
        // 내가 한거랑 똑같음. 버튼 클릭되면 다음 화면으로 넘기고, intent를 이용해 임시로 입력된 글자 저장해 놓았다가 다음 화면에 표시하는.

    }
}