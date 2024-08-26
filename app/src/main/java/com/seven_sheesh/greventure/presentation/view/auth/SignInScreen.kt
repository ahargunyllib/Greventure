package com.seven_sheesh.greventure.presentation.view.auth

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj.ParentNavObj
import com.seven_sheesh.greventure.presentation.ui.widget.GoogleSignInButton
import com.seven_sheesh.greventure.presentation.viewmodel.SignInViewModel
import io.github.jan.supabase.compose.auth.ui.annotations.AuthUiExperimental

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class, AuthUiExperimental::class)
@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: SignInViewModel = hiltViewModel(),
) {
    Scaffold(
    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
        ) {
            GoogleSignInButton()
            Button(onClick = { navController.navigate(ParentNavObj.HomeNav.route) }) {
                Text(text = "Skip for now")
            }
        }
    }
}
