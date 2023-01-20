package com.lista.desafio04.core.ui

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.lista.desafio04.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var flLoading: FrameLayout? = null

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun showLoading(show: Boolean) {
        flLoading?.visibility = if (show) View.VISIBLE else View.GONE
    }
}