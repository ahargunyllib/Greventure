package com.seven_sheesh.greventure.presentation.view.auth

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import com.seven_sheesh.greventure.presentation.ui.widget.common.ButtonSize
import com.seven_sheesh.greventure.presentation.ui.widget.common.ButtonType
import com.seven_sheesh.greventure.presentation.ui.widget.common.CustomButton
import com.seven_sheesh.greventure.presentation.ui.widget.common.Input
import com.seven_sheesh.greventure.presentation.viewmodel.RegisterViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(authNavCtr: NavController, viewModel: RegisterViewModel = hiltViewModel()) {
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val name = viewModel.name.collectAsState(initial = "")
    val email = viewModel.email.collectAsState(initial = "")
    val phoneNumber = viewModel.phoneNumber.collectAsState(initial = "")
    val password = viewModel.password.collectAsState(initial = "")
    val confirmPassword = viewModel.confirmPassword.collectAsState(initial = "")

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
                authNavCtr.navigate(AuthNavObj.Login.route)
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        containerColor = GreventureScheme.White.color,
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(
                    "Daftar", style = TextStyle(
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                )
            }, navigationIcon = {
                Icon(Icons.Outlined.ArrowBackIosNew,
                    contentDescription = "Daftar",
                    modifier = Modifier.clickable {
                        authNavCtr.navigate(AuthNavObj.Login.route)
                    })
            })
        },
        bottomBar = {
            CustomButton(
                onClick = {
                    localSoftwareKeyboardController?.hide()
                    viewModel.onRegister()
                },
                modifier = Modifier.fillMaxWidth(),
                text = "Daftar",
                size = ButtonSize.LARGE,
                isEnabled = message != "Loading...",
            )
        }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Input(
                label = "Nama",
                value = name.value,
                onValueChange = { viewModel.onNameChange(it) },
                placeholder = "John Doe",
                textStyle = TextStyle(
                    fontSize = 16.sp, fontWeight = FontWeight.W400
                ),
            )
            Spacer(modifier = Modifier.height(12.dp))
            Input(
                label = "Email",
                value = email.value,
                onValueChange = { viewModel.onEmailChange(it) },
                placeholder = "johndoe@example.com",
                textStyle = TextStyle(
                    fontSize = 16.sp, fontWeight = FontWeight.W400
                ),
                keyboardType = KeyboardType.Email
            )
            Spacer(modifier = Modifier.height(12.dp))
            Input(
                label = "No. Telepon",
                value = phoneNumber.value,
                onValueChange = { viewModel.onPhoneNumberChange(it) },
                placeholder = "081234567890",
                textStyle = TextStyle(
                    fontSize = 16.sp, fontWeight = FontWeight.W400
                ),
                keyboardType = KeyboardType.Phone
            )
            Spacer(modifier = Modifier.height(12.dp))
            Input(
                label = "Kata Sandi",
                value = password.value,
                onValueChange = { viewModel.onPasswordChange(it) },
                placeholder = "",
                textStyle = TextStyle(
                    fontSize = 16.sp, fontWeight = FontWeight.W400
                ),
                trailingContent = {
                    Icon(
                        imageVector = Icons.Default.RemoveRedEye, contentDescription = "Password"
                    )
                },
                keyboardType = KeyboardType.Password
            )
            Spacer(modifier = Modifier.height(12.dp))
            Input(
                label = "Konfirmasi Kata Sandi",
                value = confirmPassword.value,
                onValueChange = { viewModel.onConfirmPasswordChange(it) },
                placeholder = "",
                textStyle = TextStyle(
                    fontSize = 16.sp, fontWeight = FontWeight.W400
                ),
                trailingContent = {
                    Icon(
                        imageVector = Icons.Default.RemoveRedEye, contentDescription = "Password"
                    )
                },
                keyboardType = KeyboardType.Password
            )
        }
    }
}