package com.app.startupscreenjetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable

/**
 * This class is used for define all the common behaviour of activity
 * and have abstract methods, which you can override in your actual implementations.
 */

abstract class BaseActivity : ComponentActivity() {

    /**
     * Call when class has been initialize
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            BuildContent()
        }
    }

    /**
     * It will call when activity will start to init component
     */
    @Composable
    abstract fun BuildContent()

}