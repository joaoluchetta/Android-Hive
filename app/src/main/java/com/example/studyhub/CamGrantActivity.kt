@file:Suppress("DEPRECATION")

package com.example.studyhub

import androidx.appcompat.app.AppCompatActivity
import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.studyhub.databinding.ActivityCamgrantBinding
import com.google.android.material.snackbar.Snackbar

class CamGrantActivity: AppCompatActivity() {
    private lateinit var binding: ActivityCamgrantBinding


    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission())
    { isGranted: Boolean ->
        if(isGranted) {
            takePicture()
        } else {
            Snackbar.make(binding.rootCamGrant, "Permissão da câmera negada!", Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCamgrantBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.rootCamGrant)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rootCamGrant)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnCamera.setOnClickListener {
            cameraPermission()
        }

        binding.btnVoltar.setOnClickListener { rootCounter ->
            this.finish()
        }
    }

    private fun cameraPermission(){
        when {
            ContextCompat.checkSelfPermission(
                this, Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                takePicture()
            }

            shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA) -> {
                Snackbar.make(binding.rootCamGrant, "A câmera é necessária para tirar foto", Snackbar.LENGTH_LONG).show()
            }
            else ->{
                requestPermissionLauncher.launch(
                    Manifest.permission.CAMERA
                )
            }
        }
    }

    private val takePictureLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if(result.resultCode == Activity.RESULT_OK) {
            val photoBitmap = result.data?.extras?.get("data") as Bitmap
            Glide.with(this)
                .load(photoBitmap)
                .centerCrop()
                .into(binding.imgViewCamera)
        }
    }

    private fun takePicture(){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        takePictureLauncher.launch(intent)
    }
}