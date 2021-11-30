package com.example.my_pro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class Welcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome2)

        supportActionBar?.hide()

        Handler().postDelayed({
            var intent = Intent(applicationContext, About ::class.java )
            startActivity(intent)
        }, 3500)

        var intent = Intent(applicationContext, AboutFragment::class.java)
        startActivity(intent)
    }
}