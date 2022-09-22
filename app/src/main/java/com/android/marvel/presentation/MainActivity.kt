package com.android.marvel.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.marvel.R
import com.android.marvel.presentation.character.CharacterFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        if (supportFragmentManager.findFragmentByTag(CharacterFragment::class.java.name) == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.flContainer, CharacterFragment(), CharacterFragment::class.java.name)
                .commit()
        }
    }
}