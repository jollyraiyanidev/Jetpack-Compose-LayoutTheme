package com.app.startupscreenjetpack.ui.screen

import android.app.Activity
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.app.startupscreenjetpack.R
import com.app.startupscreenjetpack.model.sliding.SlidingResponse
import com.app.startupscreenjetpack.network.model.State
import com.app.startupscreenjetpack.ui.theme.StartupScreenJetpackTheme
import com.app.startupscreenjetpack.ui.theme.fonts
import com.app.startupscreenjetpack.ui.viewmodel.UserViewModel


@Composable
fun HomeScreen(
    navController: NavHostController,userModel :UserViewModel= hiltViewModel()
) {

    Scaffold(

        bottomBar = {SmootheBottomNavigation()},
    ) { padding -> Box(  modifier = Modifier
        .fillMaxSize()
    ) {
        BackgroundScreen()
        HomePage(userModel)
    }
    }
}

@Composable
fun HomePage(userModel: UserViewModel) {

    val usersState by userModel.sliders.observeAsState()

    val activity = LocalContext.current as Activity
    LaunchedEffect(Unit) {
        userModel.fetchUsers(activity)

    }
    when (usersState) {
        is State.Loading -> {
            // Show loading indicator
            Box(modifier = Modifier){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))}
        }
        is State.Success -> {
            val users = (usersState as State.Success<SlidingResponse>).data
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                contentPadding = PaddingValues(horizontal = 10.dp),
                modifier =Modifier){
                items(users){ item ->
                    SliderHorizontal(
                        drawable = item.image.toString(),
                        text = item.name.toString()
                    )
                }

            }
        }
        is State.Error -> {
            val error = (usersState as State.Error<SlidingResponse>).message
            // Show error message
            Box(modifier = Modifier){
            Text(
                text = "Failed to fetch data: $error",
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.Center)
            )}
        }

        else -> {

        }
    }


}

@Composable
fun SliderHorizontal(drawable: String, text: String) {
    Column( horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(shape = RoundedCornerShape(16.dp))
            .background(colorResource(id = R.color.transPrimary)).height(370.dp)) {
        Image(
            painter = rememberAsyncImagePainter(drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(220.dp)
                .height(300.dp)
                .fillMaxWidth()
        )
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = Color.White,
            fontFamily = fonts,
            fontWeight =  FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, top = 24.dp, bottom = 15.dp).width(200.dp)



        )

    }

}

@Composable
    fun SmootheBottomNavigation(modifier: Modifier = Modifier) {
        NavigationBar(containerColor = MaterialTheme.colorScheme.surfaceVariant,
            modifier =modifier) {
            NavigationBarItem(selected = true, onClick = { /*TODO*/ },
                icon = {Icon(Icons.Default.Home, contentDescription = null)} ,
                label = { Text(text = stringResource(id = R.string.bottom_navigation_home))})

            NavigationBarItem(selected = false, onClick = { /*TODO*/ },
                icon = { Icon(painterResource(id =R.drawable.ic_recipe), contentDescription = null) } ,
                label = { Text(text = stringResource(id = R.string.bottom_navigation_receipes))})

            NavigationBarItem(selected = false, onClick = { /*TODO*/ },
                icon = {Icon(painterResource(id =R.drawable.ic_yoga), contentDescription = null)} ,
                label = { Text(text = stringResource(id = R.string.bottom_navigation_yoga))})

            NavigationBarItem(selected = false, onClick = { /*TODO*/ },
                icon = {Icon(painterResource(id =R.drawable.widom), contentDescription = null) } ,
                label = { Text(text = stringResource(id = R.string.bottom_navigation_wisdom))})

            NavigationBarItem(selected = false, onClick = { /*TODO*/ },
                icon = {Icon(Icons.Default.Person, contentDescription = null)} ,
                label = {
                    Text(text = stringResource(id = R.string.bottom_navigation_me))})


        }
    }


@Preview(name = "Welcome light theme", uiMode = UI_MODE_NIGHT_NO)
@Composable
fun HomeScreenPreview() {
    StartupScreenJetpackTheme {
            HomeScreen(rememberNavController(), viewModel())
    }
}


@Preview(name = "Welcome light theme", uiMode = UI_MODE_NIGHT_NO)
@Composable
fun sliderPreview() {
    StartupScreenJetpackTheme {
        SliderHorizontal("https://i.pinimg.com/564x/27/4f/26/274f2689eef9688adb8809a46a4065c8.jpg", "34334343443434344343434434343443434344343434434343444344")
    }
}
