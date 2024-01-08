package com.app.startupscreenjetpack

import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.app.startupscreenjetpack.navigation.NavigationItem
import com.app.startupscreenjetpack.navigation.route.Navigation
import com.app.startupscreenjetpack.ui.screen.AutoSlide
import com.app.startupscreenjetpack.ui.theme.StartupScreenJetpackTheme
import com.app.startupscreenjetpack.ui.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    val viewModel: UserViewModel by viewModels()

    @Composable
    override fun BuildContent() {
        setContent {
            StartupScreenJetpackTheme {
                Navigation()

            }
        }

    }
}

