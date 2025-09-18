package com.example.studyhub

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studyhub.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnCounter.setOnClickListener {
            val intentCounter = Intent(this, CounterActivity::class.java)
            startActivity(intentCounter)
        }

        binding.btnShuffle.setOnClickListener {
            val intentShuffle = Intent(this, ShuffleActivity::class.java)
            startActivity(intentShuffle)
        }

        binding.btnCamGrant.setOnClickListener {
            val intentCamGrant = Intent(this, CamGrantActivity::class.java)
            startActivity(intentCamGrant)
        }

        binding.btnComingSoon.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Get Ready!")
                .setMessage("New projects are coming soon.")
                .setPositiveButton("OK", null)
                .show()
        }
    }
}