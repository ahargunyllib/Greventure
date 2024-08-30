package com.seven_sheesh.greventure.presentation.view.profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme
import com.seven_sheesh.greventure.presentation.ui.design_system.PlusJakartaSans
import com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj.AuthNavObj
import com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj.HomeNavObj
import com.seven_sheesh.greventure.presentation.ui.widget.common.ButtonSize
import com.seven_sheesh.greventure.presentation.ui.widget.common.CustomButton
import com.seven_sheesh.greventure.presentation.ui.widget.common.Input
import com.seven_sheesh.greventure.presentation.viewmodel.EditProfilViewModel
import com.seven_sheesh.greventure.presentation.viewmodel.NavbarViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
@Preview
fun EditProfileScreen(
    homeNavController: NavController = rememberNavController(),
    navbarViewModel: NavbarViewModel = hiltViewModel(),
    editProfilViewModel: EditProfilViewModel = hiltViewModel()
) {
    navbarViewModel.setPageState(3)

    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val user = navbarViewModel.user.collectAsState().value.second

    val photoUri = editProfilViewModel.photoUri.collectAsState(initial = Uri.EMPTY).value
    val name = editProfilViewModel.name.collectAsState(initial = user?.name ?: "")
    val email = editProfilViewModel.email.collectAsState(initial = user?.email ?: "")
    val phoneNumber = editProfilViewModel.phoneNumber.collectAsState(initial = user?.phoneNum ?: "")

    val message =
        editProfilViewModel.message.collectAsState(initial = "").value

    val localSoftwareKeyboardController = LocalSoftwareKeyboardController.current

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            editProfilViewModel.onPhotoUriChange(uri)
        }
    )

    LaunchedEffect(message) {
        if (message.isNotEmpty()) {
            coroutineScope.launch {
                snackBarHostState.showSnackbar(
                    message = message,
                    duration = SnackbarDuration.Short,
                )
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        containerColor = GreventureScheme.White.color,
        bottomBar = {
            CustomButton(
                onClick = {
                    localSoftwareKeyboardController?.hide()
                    editProfilViewModel.onEditProfile()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 128.dp),
                text = "Simpan",
                size = ButtonSize.LARGE,
                isEnabled = message != "Loading...",
            )
        }
    ) { _ ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .safeDrawingPadding(),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(90.dp)
                            .background(GreventureScheme.Primary.color),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .fillMaxSize(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBackIosNew,
                                contentDescription = "Arrow Back",
                                modifier = Modifier.clickable {
                                    homeNavController.navigate(HomeNavObj.EditProfileScreen.route)
                                },
                                tint = GreventureScheme.White.color
                            )
                            Column {
                                Text(
                                    text = "Edit Profil",
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 22.sp,
                                    color = GreventureScheme.White.color,
                                    style = TextStyle(
                                        fontFamily = PlusJakartaSans,
                                    )
                                )
                            }
                            Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                        }
                    }
                }

                item {
                    Card(
                        modifier = Modifier
                            .padding(top = 32.dp)
                            .size(100.dp)
                            .clickable {
                                singlePhotoPickerLauncher.launch(
                                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                )
                            },
                        shape = RoundedCornerShape(50),
                        colors = CardDefaults.cardColors(GreventureScheme.White.color),
                        border = BorderStroke(2.dp, GreventureScheme.SoftGray.color)
                    ) {
                        if (photoUri != Uri.EMPTY) {
                            Image(
                                painter = rememberAsyncImagePainter(photoUri),
                                contentDescription = "Profile Picture",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        } else if (user?.profilePictureUrl != null) {
                            AsyncImage(
                                model = user.profilePictureUrl,
                                contentDescription = "Profile Picture",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Input(
                            label = "Nama",
                            value = name.value,
                            onValueChange = { editProfilViewModel.onNameChange(it) },
                            placeholder = user?.name ?: "Namanya gada",
                            textStyle = TextStyle(
                                fontSize = 16.sp, fontWeight = FontWeight.W400
                            ),
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Input(
                            label = "Email",
                            value = email.value,
                            onValueChange = { editProfilViewModel.onEmailChange(it) },
                            placeholder = user?.email ?: "Emailnya gada",
                            textStyle = TextStyle(
                                fontSize = 16.sp, fontWeight = FontWeight.W400
                            ),
                            keyboardType = KeyboardType.Email
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Input(
                            label = "No. Telepon",
                            value = phoneNumber.value,
                            onValueChange = { editProfilViewModel.onPhoneNumberChange(it) },
                            placeholder = user?.phoneNum ?: "Nomornya gada",
                            textStyle = TextStyle(
                                fontSize = 16.sp, fontWeight = FontWeight.W400
                            ),
                            keyboardType = KeyboardType.Phone
                        )
                    }
                }
            }
        }
    }
}