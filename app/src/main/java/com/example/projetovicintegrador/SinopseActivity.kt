package com.example.projetovicintegrador

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.sinopse.*

class SinopseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sinopse)
    cardView.setBackgroundResource(R.drawable.bg_left_rounded)
    }
}