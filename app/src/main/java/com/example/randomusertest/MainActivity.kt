package com.example.randomusertest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.randomusertest.presentation.UserListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.userList_fragment_container, UserListFragment())
                .commit()
        }
    }
}