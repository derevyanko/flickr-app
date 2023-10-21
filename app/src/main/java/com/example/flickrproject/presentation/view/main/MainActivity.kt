package com.example.flickrproject.presentation.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flickrproject.R
import com.example.flickrproject.presentation.view.recent.RecentPhotosFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerViewMain, RecentPhotosFragment())
            .commit()
    }
}