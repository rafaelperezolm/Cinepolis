package com.rafaelperezolm.cinepolis.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rafaelperezolm.cinepolis.databinding.ActivityLoginBinding
import com.rafaelperezolm.cinepolis.ui.construction.UnderConstructionActivity
import dagger.hilt.android.AndroidEntryPoint

//Class that contains a few login options, in this case the only functional will be the Email login
@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    //View Binding to vinculate the xml components to the code
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Google login function
        binding.loginBtnGoogle.setOnClickListener {
            val intent: Intent = Intent(this, UnderConstructionActivity::class.java)
            startActivity(intent)
        }

        //Register login function
        binding.loginTxvRegLink.setOnClickListener {
            val intent: Intent = Intent(this, UnderConstructionActivity::class.java)
            startActivity(intent)
        }

        //Email login function
        binding.loginBtnEmail.setOnClickListener {
            val intent: Intent = Intent(this, LoginEmailActivity::class.java)
            startActivity(intent)
        }

    }

}