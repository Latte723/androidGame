package com.example.high_and_low.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.high_and_low.R

class TitleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.title)
    }

    fun onStart(view: View?) {
        val intent: Intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }

    fun onClickCredit(view: View?) {
        val intent: Intent = Intent(this, CreditActivity::class.java)
        startActivity(intent)
    }


}