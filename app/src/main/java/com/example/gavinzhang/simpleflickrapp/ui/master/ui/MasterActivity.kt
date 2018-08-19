package com.example.gavinzhang.simpleflickrapp.ui.master.ui

import android.os.Bundle
import androidx.navigation.Navigation
import com.example.gavinzhang.simpleflickrapp.R
import dagger.android.support.DaggerAppCompatActivity

class MasterActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.master_activity)

    }

    override fun onSupportNavigateUp()
            = Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp()
}
