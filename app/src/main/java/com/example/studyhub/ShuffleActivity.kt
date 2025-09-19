package com.example.studyhub

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.example.studyhub.databinding.ActivityShuffleBinding
import com.google.android.material.snackbar.Snackbar

private val urlList = listOf("https://picsum.photos/id/1015/600/400",
    "https://picsum.photos/id/1025/600/400",
    "https://picsum.photos/id/1035/600/400",
    "https://picsum.photos/id/1045/600/400",
    "https://picsum.photos/id/1055/600/400",
    "https://picsum.photos/id/1065/600/400",
    "https://picsum.photos/id/1075/600/400",
    "https://picsum.photos/id/1085/600/400",
)

class ShuffleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShuffleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShuffleBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.rootShuffle)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rootShuffle)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val screenWidth = resources.displayMetrics.widthPixels
        val circularProgressDrawable = CircularProgressDrawable(this)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 400/6f
        circularProgressDrawable.start()

        val shuffle = urlList.random()
        Glide.with(this)
            .load(shuffle)
            .override(screenWidth, screenWidth)
            .placeholder(circularProgressDrawable)
            .error(R.drawable.img_error)
            .centerCrop()
            .into(binding.imageView)

        binding.btnImage.setOnClickListener {
            val newShuffle = urlList.random()
            Glide.with(this)
                .load(newShuffle)
                .override(screenWidth, screenWidth)
                .placeholder(circularProgressDrawable)
                .error(R.drawable.img_error)
                .centerCrop()
                .listener(object : com.bumptech.glide.request.RequestListener<android.graphics.drawable.Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: com.bumptech.glide.request.target.Target<android.graphics.drawable.Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        Snackbar.make(binding.rootShuffle, "Failed to load image", Snackbar.LENGTH_SHORT).show()
                        return false // retorna false para que o Glide ainda mostre o drawable de erro
                    }

                    override fun onResourceReady(
                        resource: android.graphics.drawable.Drawable?,
                        model: Any?,
                        target: com.bumptech.glide.request.target.Target<android.graphics.drawable.Drawable>?,
                        dataSource: com.bumptech.glide.load.DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }
                })
                .into(binding.imageView)
        }

        binding.btnReturn.setOnClickListener { rootShuffle ->
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