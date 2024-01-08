package com.app.startupscreenjetpack.ui.screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.startupscreenjetpack.R
import com.app.startupscreenjetpack.navigation.NavigationItem
import com.app.startupscreenjetpack.ui.theme.StartupScreenJetpackTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun IndicatorDot(
    modifier: Modifier = Modifier,
    size: Dp,
    color: Color
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(color)
    )
}

@Composable
fun DotsIndicator(
    modifier: Modifier = Modifier,
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color = colorResource(id = R.color.white) /* Color.Yellow */,
    unSelectedColor: Color = colorResource(id = android.R.color.darker_gray) /* Color.Gray */,
    dotSize: Dp
) {
    LazyRow(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
    ) {
        items(totalDots) { index ->
            IndicatorDot(
                color = if (index == selectedIndex) selectedColor else unSelectedColor,
                size = dotSize
            )

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 3.dp))
            }
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun AutoSlidingCarousel(
    modifier: Modifier = Modifier,
    autoSlideDuration: Long = 2000,
    navController: NavHostController,
    itemsCount: Int,
    itemContent: @Composable (index: Int) -> Unit,
) {
    val pagerState = rememberPagerState(0)
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
    var key by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = key) {
        launch {
            delay(autoSlideDuration)
            with(pagerState) {
                val target = if (currentPage < pageCount - 1) currentPage + 1 else 0
                animateScrollToPage(page = target) //Broken
                key = !key
            }
        }
    }

    Box(
        modifier = modifier.fillMaxSize(),

    ) {
        HorizontalPager(count = itemsCount, state = pagerState,
        ) { page ->
            itemContent(page)
        }

        // you can remove the surface in case you don't want
        // the transparant bacground

        Button(shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 115.dp, start = 30.dp,end =30.dp).fillMaxWidth(1f),

            onClick = {

                    navController.navigate(NavigationItem.Login.route)


            }
        ) {

            Icon(
                imageVector = Icons.Filled.Email,
                modifier = Modifier.size(14.dp),
                contentDescription = "drawable icons",
                tint = Color.White
            )
            Text(text = "Continue with Email" ,
                color = colorResource(id = R.color.white),
                modifier = Modifier.padding(start = 10.dp))

        }

        Surface(
            modifier = Modifier
                .padding(bottom = 215.dp)
                .align(Alignment.BottomCenter),
                shape = CircleShape,
                color = Color.Black.copy(alpha = 0.5f)
        )
        {
            DotsIndicator(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                totalDots = itemsCount,
                selectedIndex = if (isDragged) pagerState.currentPage else pagerState.pageCount,
                dotSize = 6.dp
            )
        }

    }
}
@Composable
fun AutoSlide(navController: NavHostController){

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {

        val images = listOf(
            R.drawable.slider_1 to R.string.slide_1,
            R.drawable.slider_3 to R.string.slide_3,
            R.drawable.slider_2 to R.string.slide_2,
            R.drawable.slide_4 to R.string.slide_4).map { DrawableStringPair(it.first, it.second) }

        AutoSlidingCarousel(
            navController = navController,
            itemsCount = images.size,

            ) { index ->

            Box {
                Image(
                painter = painterResource(id = images[index].drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Text (
                text = stringResource(id = images[index].text),
                fontFamily = FontFamily.Serif,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight(align = Alignment.CenterVertically)
                    .padding(top = 180.dp),
                color = colorResource(id = R.color.white),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold


            )}


        }
    }

}


private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)


@Preview(name = "Welcome light theme", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun WelcomeScreenPreview() {
    StartupScreenJetpackTheme {
            AutoSlide(rememberNavController())
    }
}
