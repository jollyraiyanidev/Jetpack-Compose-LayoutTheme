package com.app.startupscreenjetpack.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.startupscreenjetpack.R
import com.app.startupscreenjetpack.ui.theme.StartupScreenJetpackTheme

@Composable
fun BackgroundScreen() {
    Box(modifier = Modifier
        .background(color = colorResource(id = R.color.primary))
        .fillMaxSize()) {

        Canvas(
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.TopEnd)
                .padding(top = 100.dp, end = 100.dp),
            onDraw = {

                drawCircle(
                    color = Color(0xFF283123),
                    radius = 100f)

            }
        )
        Canvas(
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.CenterStart)
                .padding(bottom = 180.dp),
            onDraw = {

                drawCircle(
                    color = Color(0xFF283123),
                    radius = 350f)

            }
        )
        Canvas(
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.CenterEnd)
                .padding(top = 150.dp, end = 20.dp),
            onDraw = {

                drawCircle(
                    color = Color(0xFF283123),
                    radius = 200f)

            }
        )

        Canvas(
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.BottomEnd),
            onDraw = {

                drawCircle(
                    color = Color(0xFF283123),
                    radius = 350f)

            }
        )



    }

}
@Preview(name = "Welcome light theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun BackgroundScreenPreview() {
    StartupScreenJetpackTheme {
        BackgroundScreen()
    }
}
