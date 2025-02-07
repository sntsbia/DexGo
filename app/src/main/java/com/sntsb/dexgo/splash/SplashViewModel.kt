package com.sntsb.dexgo.splash

import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import com.sntsb.dexgo.main.MainActivity

class SplashViewModel : ViewModel() {

    private var initiated = false

    fun initApp(activity: ComponentActivity) {
        if (!initiated) {

            Handler(Looper.getMainLooper()).postDelayed({
                activity.startActivity(Intent(activity, MainActivity::class.java))
                activity.finish()
            }, 2500)

            initiated = true
        }
    }

}