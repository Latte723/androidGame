package com.example.high_and_low.activity

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity

abstract class BackHomeActivity : AppCompatActivity() {
    fun onBackButton(view: View?) {
        val intent: Intent = Intent(this, TitleActivity::class.java)
        startActivity(intent)
    }
}