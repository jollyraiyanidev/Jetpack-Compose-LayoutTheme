package com.app.startupscreenjetpack

import android.annotation.SuppressLint
import android.app.Application
import com.google.gson.Gson
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp

class HiltApplication : Application() {

    companion object {
        lateinit var applicationInstance: HiltApplication
    }
    @SuppressLint("WrongConstant")
    override fun onCreate() {
        super.onCreate()
        applicationInstance =this


//        Kotpref.init(applicationContext)
//        Kotpref.gson = Gson()

       // FirebaseApp.initializeApp(this)

        /*FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("FCM", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            AppPreference.FCM_TOKEN = token.toString()
            // Log and toast
            Log.d("FCM TOKEN", token.toString())
        })*/

    }



}