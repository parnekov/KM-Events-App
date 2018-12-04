package com.parnekov.sasha.kmcityevents.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.parnekov.sasha.kmcityevents.R

const val SPLASH_DISPLAY_TIME: Long = 1000 // milliseconds

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        splash()
    }

    private fun splash() {
        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(this, KMSignInActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_DISPLAY_TIME)
    }
}
