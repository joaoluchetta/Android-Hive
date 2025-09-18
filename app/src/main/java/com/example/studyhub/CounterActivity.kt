package com.example.studyhub

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studyhub.databinding.ActivityCounterBinding
import com.google.android.material.snackbar.Snackbar


class CounterActivity : AppCompatActivity(){
    private var count = 0
    private lateinit var binding: ActivityCounterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCounterBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.rootCounter)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rootCounter)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnPlus.setOnClickListener{ rootCounter ->
            count++
            binding.textCounter.text = count.toString()
        }

        binding.btnMinus.setOnClickListener{ rootCounter ->
            count--
            if(count >= 0)
                binding.textCounter.text = count.toString()
            else {
                count = 0
                Snackbar.make(rootCounter, "Negative numbers are not allowed.", Snackbar.LENGTH_SHORT).show()
            }
            binding.textCounter.text = count.toString()
        }

        binding.btnReturn.setOnClickListener { rootCounter ->
            this.finish()
        }
    }

    override fun onStart(){
        super.onStart()
        Log.d("LIFECICYLE", "ON_START" )
    }

    override fun onResume() {
        super.onResume()
        Log.d("LIFECICYLE", "ON_RESUME" )
    }

    override fun onPause() {
        super.onPause()
        Log.d("LIFECICYLE", "ON_PAUSE" )
    }

    override fun onStop() {
        super.onStop()
        Log.d("LIFECICYLE", "ON_STOP" )
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LIFECICYLE", "ON_DESTROY" )
    }

}