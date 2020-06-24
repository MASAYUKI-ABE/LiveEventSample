package com.example.liveeventsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Display first page
        transitNextPage(1)
    }

    fun transitNextPage(pageNo: Int) {
        supportFragmentManager.commit {
            replace(R.id.fragment_container, MainFragment.newInstance(pageNo), MainFragment.TAG)
            addToBackStack(MainFragment.TAG)
        }
    }
}