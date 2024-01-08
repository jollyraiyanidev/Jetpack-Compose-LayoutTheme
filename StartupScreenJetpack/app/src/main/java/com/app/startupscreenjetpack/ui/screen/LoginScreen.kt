package com.app.startupscreenjetpack.ui.screen

import android.app.Activity
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.startupscreenjetpack.R
import com.app.startupscreenjetpack.navigation.NavigationItem
import com.app.startupscreenjetpack.ui.theme.StartupScreenJetpackTheme
import com.app.startupscreenjetpack.ui.theme.customTitle
import com.app.startupscreenjetpack.ui.theme.fonts
import okhttp3.internal.wait


@Composable
fun LoginScreen(navController: NavHostController) {


    Box(modifier = Modifier
        .fillMaxSize()
        ) {
            BackgroundScreen()

            val activity = (LocalContext.current as? Activity)
            Image(
                painterResource(R.drawable.ic_arrow_back),
                contentDescription = "",
                modifier = Modifier
                    .padding(20.dp)
                    .clickable { activity?.finish() },
            )


        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val username = remember { mutableStateOf(TextFieldValue()) }
            val keyboardController = LocalSoftwareKeyboardController.current

            Text(
                text = "Sign in",
                color = Color.White,
                style = typography.customTitle,

                )
            Spacer(modifier = Modifier.height(40.dp))
            TextField(
                label = { Text(text = "E-mail") },
                placeholder = { Text(text = "E-mail") },
                leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null)},
                value = username.value,
                onValueChange = { username.value = it },
                shape = RoundedCornerShape(25.dp),
                modifier = Modifier.fillMaxWidth().padding(start = 30.dp, end = 30.dp).border(
                    width = 2.dp,
                    shape = RoundedCornerShape(25.dp),
                    color = colorResource(id = R.color.primaryLight)
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = { keyboardController?.hide() }
                ),
                colors = TextFieldDefaults.colors(
                    cursorColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    disabledTextColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.Black,
                    unfocusedContainerColor = Color.Black,
                    focusedPlaceholderColor = Color.White,
                    disabledPlaceholderColor = Color.White,
                    unfocusedPlaceholderColor = Color.White,
                    focusedTextColor = Color.White


                )
            )
            Spacer(modifier = Modifier.height(15.dp))

                Button(
                    onClick = { navController.navigate(NavigationItem.Home.route)},
                    colors = ButtonDefaults.buttonColors(
                        containerColor= Color.Transparent
                    ),
                    shape = RoundedCornerShape(25.dp),

                ) {
                    Text(
                        text = "Sign-In",

                        modifier = Modifier
                            .fillMaxWidth().padding(12.dp)
                            .background(
                                Brush.horizontalGradient(
                                    colors =
                                    listOf(
                                        colorResource(id = R.color.primaryLight),
                                        Color.Green
                                    )
                                ), shape = RoundedCornerShape(25.dp)
                            )
                            .align(Alignment.CenterVertically)
                            .padding(top =12.dp, bottom = 12.dp),
                        textAlign = TextAlign.Center,
                        fontFamily = fonts,
                        letterSpacing = 2.sp,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold)

            }
            //  Text (text = "3434fdf",)
        }
    }


}



@Preview(name = "Welcome light theme", uiMode = UI_MODE_NIGHT_NO)
@Composable
fun LoginScreenPreview() {
    StartupScreenJetpackTheme {
            LoginScreen(rememberNavController())
    }
}
