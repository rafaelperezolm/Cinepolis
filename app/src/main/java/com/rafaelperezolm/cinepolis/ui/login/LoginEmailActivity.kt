package com.rafaelperezolm.cinepolis.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rafaelperezolm.cinepolis.R
import com.rafaelperezolm.cinepolis.databinding.ActivityLoginEmailBinding
import dagger.hilt.android.AndroidEntryPoint

//This class contains the login email fragment for access
@AndroidEntryPoint
class LoginEmailActivity : AppCompatActivity() {

    //View Binding to vinculate the xml components to the code
    private lateinit var binding: ActivityLoginEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginEmailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setToolBar()
    }

    //Setting the toolbar
    private fun setToolBar() {
        setSupportActionBar(binding.loginTlbActions)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        this.title = resources.getText(R.string.login_title_text)
    }

    //Assign back press action to the toolbar return arrow
    override fun onSupportNavigateUp(): Boolean {
        super.onBackPressed()
        return true
    }

}