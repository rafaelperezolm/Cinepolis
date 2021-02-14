package com.rafaelperezolm.cinepolis.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rafaelperezolm.cinepolis.R
import com.rafaelperezolm.cinepolis.ui.login.LoginActivity
import com.rafaelperezolm.cinepolis.ui.main.MainActivity
import com.rafaelperezolm.cinepolis.utils.SessionManager

//Splash screen, shows the app logo
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val sessionManager = SessionManager(applicationContext)
        //If the user is alredy logged is redirected to the Profile
        //Else shows the login options screen
        val intent: Intent = if (sessionManager.isLoggedIn()) {
            Intent(applicationContext, MainActivity::class.java)
        } else {
            Intent(applicationContext, LoginActivity::class.java)
        }
        startActivity(intent)
        finish()
    }

}