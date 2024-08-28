package com.seven_sheesh.greventure.presentation.view.auth

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.seven_sheesh.greventure.R
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme
import com.seven_sheesh.greventure.presentation.ui.design_system.PlusJakartaSans
import com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj.AuthNavObj
import com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj.ParentNavObj
import com.seven_sheesh.greventure.presentation.ui.widget.GoogleSignInButton
import com.seven_sheesh.greventure.presentation.ui.widget.common.ButtonSize
import com.seven_sheesh.greventure.presentation.ui.widget.common.ButtonType
import com.seven_sheesh.greventure.presentation.ui.widget.common.CustomButton
import com.seven_sheesh.greventure.presentation.ui.widget.common.Input
import com.seven_sheesh.greventure.presentation.viewmodel.LoginViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun LoginScreen(authNavCtr: NavController, viewModel: LoginViewModel = hiltViewModel()) {
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val email = viewModel.email.collectAsState(initial = "")
    val password = viewModel.password.collectAsState(initial = "")

    val message =
        viewModel.message.collectAsState(initial = "").value

    val localSoftwareKeyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(message) {
        if (message.isNotEmpty()) {
            coroutineScope.launch {
                snackBarHostState.showSnackbar(
                    message = message,
                    duration = SnackbarDuration.Short,
                )
            }

            if (message.contains("Successfully")) {
                delay(1000)
                authNavCtr.navigate(AuthNavObj.Home.route)
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        containerColor = GreventureScheme.White.color,
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.splash_logo),
                contentDescription = "Logo",
                modifier = Modifier.padding(16.dp)
            )
            Input(
                label = "E-mail",
                value = email.value,
                onValueChange = { viewModel.onEmailChange(it) },
                placeholder = "Email",
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400
                ),
            )
            Spacer(modifier = Modifier.height(12.dp))
            Input(
                label = "Password",
                value = password.value,
                onValueChange = { viewModel.onPasswordChange(it) },
                placeholder = "Password",
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400
                ),
                trailingContent = {
                    Icon(
                        imageVector = Icons.Default.RemoveRedEye,
                        contentDescription = "Password"
                    )
                },
                keyboardType = KeyboardType.Password
            )
            Spacer(modifier = Modifier.height(40.dp))
            CustomButton(
                onClick = {
                    localSoftwareKeyboardController?.hide()
                    viewModel.onSignIn()
                    coroutineScope.launch {
                        message?.let {
                            snackBarHostState.showSnackbar(
                                message = it,
                                duration = SnackbarDuration.Short
                            )
                        }
                    }
              },
                text = "Masuk",
                size = ButtonSize.LARGE,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("atau", style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Light))
            Spacer(modifier = Modifier.height(16.dp))
            GoogleSignInButton()
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    "Belum punya akun?",
                    style = TextStyle(
                        fontFamily = PlusJakartaSans,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light
                    )
                )
                CustomButton(
                    onClick = {
                        authNavCtr.navigate(AuthNavObj.Register.route)
                    },
                    text = "Daftar", type = ButtonType.TEXT, modifier = Modifier
                )

            }
        }
    }
}