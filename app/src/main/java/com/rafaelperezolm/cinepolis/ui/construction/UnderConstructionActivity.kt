package com.rafaelperezolm.cinepolis.ui.construction

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rafaelperezolm.cinepolis.R
import com.rafaelperezolm.cinepolis.databinding.ActivityUnderConstructionBinding
import dagger.hilt.android.AndroidEntryPoint

// Class that contains an "under construction" notice to assign "incomplete" or "in-process" activities,
// in this case it is just a curiosity
@AndroidEntryPoint
class UnderConstructionActivity : AppCompatActivity() {

    //View Binding to vinculate the xml components to the code
    private lateinit var binding: ActivityUnderConstructionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUnderConstructionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setToolBar()
    }

    //Setting the toolbar
    private fun setToolBar() {
        setSupportActionBar(binding.constTlbActions)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        this.title = resources.getText(R.string.app_construction)
    }

    //Assign back press action to the toolbar return arrow
    override fun onSupportNavigateUp(): Boolean {
        super.onBackPressed()
        return true
    }

}