package com.example.studyhub

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studyhub.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

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

        binding.btnComingSoon.setOnClickListener {
            val builder = AlertDialog.Builder(this) // 'this' é a Activity
            builder.setTitle("Coming Soon!")           // título da janelinha
            builder.setMessage("Stay Tuned – New Projects Coming Soon") // mensagem
            builder.setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss() // fecha a janelinha ao clicar em OK
            }
            builder.setCancelable(true) // permite fechar ao clicar fora da caixa
            builder.show()
        }
    }
}