package com.example.studyhub

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studyhub.databinding.ActivityCounterBinding
import com.google.android.material.snackbar.Snackbar


class CounterActivity : AppCompatActivity(){
    private var contador = 0
    private lateinit var binding: ActivityCounterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCounterBinding.inflate(layoutInflater)
        setContentView(binding.rootCounter)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rootCounter)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        val textContador: TextView = findViewById(R.id.textContador)
//        val btnPlus: Button = findViewById(R.id.btnPlus)
//        val btnMinus: Button = findViewById(R.id.btnMinus)

        binding.btnPlus.setOnClickListener{ rootCounter ->
            contador++
            binding.textContador.text = contador.toString()
        }

        binding.btnMinus.setOnClickListener{ rootCounter ->
            contador--
            if(contador >= 0)
                binding.textContador.text = contador.toString()
            else {
                contador = 0
                //Toast.makeText(this, "Números negativos não são permitidos", Toast.LENGTH_SHORT).show()
                Snackbar.make(rootCounter, "Numeros negativos não são permitidos", Snackbar.LENGTH_SHORT).show()
            }
            binding.textContador.text = contador.toString()
        }

        binding.btnVoltar.setOnClickListener { rootCounter ->
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