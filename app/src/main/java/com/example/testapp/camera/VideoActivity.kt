package com.example.testapp.camera

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
import android.content.res.Configuration
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.MediaController
import android.widget.RelativeLayout
import android.widget.VideoView
import androidx.annotation.RequiresApi
import com.example.testapp.R
import com.example.testapp.databinding.ActivityLoginBinding
import com.example.testapp.databinding.ActivityVideoBinding
import kotlin.math.log

class VideoActivity : Activity() {
    private val TAG = "VideoActivity"
    lateinit var binding: ActivityVideoBinding
    lateinit var videoView: VideoView

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        videoView = binding.videoView
        val uri = Uri.parse("data/data/com.example.testapp/1.mp4")
        videoView.setVideoURI(uri)
        val mediaController = MediaController(this)
        videoView.setMediaController(mediaController)
        mediaController.setMediaPlayer(videoView)
        mediaController.setVisibility(View.GONE)
        binding.btnFull.setOnClickListener {
            Log.i(TAG, "当前屏幕: " + requestedOrientation)
            if (0 == requestedOrientation) {
                requestedOrientation = 0

            } else {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

            }
        }

        videoView.start()

    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.e(TAG, "屏幕变化: "+ newConfig.orientation)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.i(TAG, "onConfigurationChanged: ORIENTATION_LANDSCAPE")
            window.insetsController!!.show((WindowInsets.Type.statusBars()))
            binding.videoView.layoutParams = FrameLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )

        } else {
            Log.i(TAG, "onConfigurationChanged: ORIENTATION_PORTRAIT")
//            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            window.insetsController!!.hide(WindowInsets.Type.statusBars())
            val params = FrameLayout.LayoutParams(
                5000,
                5000
            )
            binding.videoView.layoutParams = params
        }

    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.getResources().getDisplayMetrics().density
        return (dpValue * scale + 0.5f).toInt()
    }
}