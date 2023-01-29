package com.example.managementstudent.presentation.ui.Home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.managementstudent.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var viewBinding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }
}