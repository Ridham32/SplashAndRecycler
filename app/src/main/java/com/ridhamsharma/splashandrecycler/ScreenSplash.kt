package com.ridhamsharma.splashandrecycler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.logging.Handler

class ScreenSplash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen_splash)
        android.os.Handler().postDelayed({
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            this.finish()//ticket to go to next screen
        },2000)

        }
    }
