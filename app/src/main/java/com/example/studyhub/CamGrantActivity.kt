@file:Suppress("DEPRECATION")

package com.example.studyhub

import androidx.appcompat.app.AppCompatActivity
import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.studyhub.databinding.ActivityCamgrantBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import java.io.File

class CamGrantActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCamgrantBinding
    private lateinit var photoUri: Uri

    //Laucher para tirar foto
    private val takePictureLauncher = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success: Boolean ->
        if (success) {
            Glide.with(this)
                .load(photoUri)
                .centerCrop()
                .into(binding.imgViewCamera)
        } else {
            Snackbar.make(binding.rootCamGrant, "Captura cancelada", Snackbar.LENGTH_SHORT).show()
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission())
        { isGranted: Boolean ->
            if (isGranted) {
                takePicture()
            } else {
                Snackbar.make(
                    binding.rootCamGrant,
                    "Permission Denied!",
                    Snackbar.LENGTH_LONG
                ).show()
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

        binding.btnCamera.setOnClickListener { cameraPermission() }

        binding.btnReturn.setOnClickListener { finish() }
    }

    //Cria o arquivo para salvar a imagem
    private fun createImageFile(): Uri {
        val photoFile = File.createTempFile(
            "IMG_", ".jpg",
            getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        )

        return FileProvider.getUriForFile(
            this,
            "${applicationContext.packageName}.provider",
            photoFile
        )
    }

    //Passa o Uri do arquivo para a função de tirar a foto
    private fun takePicture() {
        photoUri = createImageFile()
        takePictureLauncher.launch(photoUri)
    }

    private fun cameraPermission() {
        when {
            //Permissão já condedida
            ContextCompat.checkSelfPermission(
                this, Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                takePicture()
            }

            //Explica para usuário caso tenha negado anteriormente
            shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                MaterialAlertDialogBuilder(this)
                    .setTitle("Permission Required")
                    .setMessage("Camera access is needed to take photos.\nIf you deny this, you will need to enable it in your device settings.")
                    .setPositiveButton("Ok") { _, _ ->
                        requestPermissionLauncher.launch(Manifest.permission.CAMERA)
                    }
                    .show()
            }

            //Solicita a permissão
            else -> {
                requestPermissionLauncher.launch(
                    Manifest.permission.CAMERA
                )
            }
        }
    }
}