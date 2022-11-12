package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class BrandNewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brand_new)
        val s = intent.getIntExtra(MainActivity.EXTRA_KEY, 0)
        val textView = findViewById<TextView>(R.id.textView2)
        textView.text = s.toString()
    }
}