package com.seven_sheesh.greventure.presentation.view.profile

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.seven_sheesh.greventure.R
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme
import com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj.HomeNavObj
import com.seven_sheesh.greventure.presentation.ui.widget.common.Input
import com.seven_sheesh.greventure.presentation.viewmodel.CreateBubbleViewModel
import com.seven_sheesh.greventure.presentation.viewmodel.NavbarViewModel

@Composable
@Preview
fun CreateBubbleScreen2(
    homeNavController: NavController = rememberNavController(),
    navbarViewModel: NavbarViewModel = hiltViewModel(),
    createBubbleViewModel: CreateBubbleViewModel = hiltViewModel(),
){
    navbarViewModel.setPageState(3)
    val bubble = createBubbleViewModel.bubbleState.collectAsState()
    val bubblePhoto = createBubbleViewModel.bubblePhoto.collectAsState()
    val bubbleSocialMedia = createBubbleViewModel.bubbleSocialMedia.collectAsState()

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            createBubbleViewModel.updateBubblePhoto(bubblePhoto.value.copy(url = uri.toString()))
        }
    )

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .safeDrawingPadding(),
        contentAlignment = Alignment.Center) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ){
            item {
                Row(modifier = Modifier
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
                    ){
                        Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "Arrow Back", modifier = Modifier.clickable {
                            homeNavController.navigate(HomeNavObj.CreateBubble1.route)
                        }, tint = GreventureScheme.White.color)
                        Column {
                            Text(text = "Pendaftaran", fontWeight = FontWeight.SemiBold, fontSize = 22.sp, color = GreventureScheme.White.color)
                        }
                        Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                    }
                }
            }

            item {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp), contentAlignment = Alignment.Center){
                    Image(painter = painterResource(id = R.drawable.create_bubble_2), contentDescription = "step 1", modifier = Modifier.height(72.dp))
                }
            }

            item {
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                    colors = CardDefaults.cardColors(GreventureScheme.White.color),
                    border = BorderStroke(2.dp, GreventureScheme.SoftGray.color)
                ) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)) {
                        Row(modifier = Modifier
                            .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
                            Column(modifier = Modifier.fillMaxWidth(0.5f)) {
                                Input(value = bubble.value.latitude.toString(), onValueChange = {
                                   createBubbleViewModel.updateBubble(bubble.value.copy(latitude = it.toDouble()))
                                }, label = "Latitude", keyboardType = KeyboardType.Decimal)
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Column() {
                                Input(value = bubble.value.longitude.toString(), onValueChange = {
                                   createBubbleViewModel.updateBubble(bubble.value.copy(longitude = it.toDouble()))
                                }, label = "Longitude", keyboardType = KeyboardType.Decimal)
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Card(modifier = Modifier
                            .fillMaxWidth()
                            .height(42.dp),
                            colors = CardDefaults.cardColors(GreventureScheme.Primary.color),
                            shape = RoundedCornerShape(50.dp)
                        ) {
                            Box(modifier = Modifier
                                .fillMaxSize()
                                .clickable {
                                    homeNavController.navigate(HomeNavObj.CreateBubblePick.route)
                                }, contentAlignment = Alignment.Center){
                                Text(text = "Atau pilih di Peta", color = GreventureScheme.White.color, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))
                        HorizontalDivider()
                        Spacer(modifier = Modifier.height(16.dp))

                        Input(value = bubble.value.phoneNumber, onValueChange = {
                           createBubbleViewModel.updateBubble(bubble.value.copy(phoneNumber = it))
                        }, label = "Nomor Telepon", keyboardType = KeyboardType.Phone, placeholder = "Masukkan nomor telepon")


                        Spacer(modifier = Modifier.height(24.dp))
                        HorizontalDivider()
                        Spacer(modifier = Modifier.height(16.dp))

                        Text(text = "Media Sosial", fontWeight = FontWeight.SemiBold, fontSize = 16.sp, color = GreventureScheme.Black.color)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Masukkkan setidaknya salah satu media sosial:", fontSize = 12.sp, color = GreventureScheme.Gray.color)
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.CameraAlt, contentDescription = "Instagram", tint = GreventureScheme.PrimaryVariant3.color, modifier = Modifier
                                .size(48.dp)
                                .padding(top = 4.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Input(value = bubbleSocialMedia.value.first.content, onValueChange = {
                               createBubbleViewModel.updateBubbleSocialMedia(
                                   bubbleSocialMedia = bubbleSocialMedia.value.first.copy(
                                       content = it
                                   ),
                                   index = 0
                               )
                            }, placeholder = "Instagram")
                        }

                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.MusicNote, contentDescription = "Tiktok", tint = GreventureScheme.PrimaryVariant3.color, modifier = Modifier
                                .size(48.dp)
                                .padding(top = 4.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Input(value = bubbleSocialMedia.value.second.content, onValueChange = {
                                createBubbleViewModel.updateBubbleSocialMedia(
                                    bubbleSocialMedia = bubbleSocialMedia.value.second.copy(
                                        content = it
                                    ),
                                    index = 1
                                )
                            }, placeholder = "Tiktok")
                        }

                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.Facebook, contentDescription = "Facebook", tint = GreventureScheme.PrimaryVariant3.color, modifier = Modifier
                                .size(48.dp)
                                .padding(top = 4.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Input(value = bubbleSocialMedia.value.third.content, onValueChange = {
                                createBubbleViewModel.updateBubbleSocialMedia(
                                    bubbleSocialMedia = bubbleSocialMedia.value.third.copy(
                                        content = it
                                    ),
                                    index = 2
                                )
                            }, placeholder = "Facebook")
                        }

                        Spacer(modifier = Modifier.height(24.dp))
                        HorizontalDivider()
                        Spacer(modifier = Modifier.height(8.dp))

                        Card(modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                            colors = CardDefaults.cardColors(GreventureScheme.Primary.color),
                            shape = RoundedCornerShape(50.dp)
                        ) {
                            Box(modifier = Modifier
                                .fillMaxSize()
                                .clickable {
                                    homeNavController.navigate(HomeNavObj.CreateBubble3.route)
                                }, contentAlignment = Alignment.Center){
                                Text(text = "Lanjut", color = GreventureScheme.White.color, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "Lampirkan Dokumentasi (optional)", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = GreventureScheme.Black.color)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Dokumentasi terkait event", fontSize = 12.sp, color = GreventureScheme.Gray.color)
                        Spacer(modifier = Modifier.height(16.dp))
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .border(
                                BorderStroke(2.dp, GreventureScheme.SoftGray.color),
                                RoundedCornerShape(16.dp)
                            )
                            .clickable {
                                singlePhotoPickerLauncher.launch(
                                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                )
                            },
                            contentAlignment = Alignment.Center,
                        ){
                            Icon(imageVector = Icons.Outlined.Image, contentDescription = "Image", tint = GreventureScheme.SoftGray.color, modifier = Modifier.size(60.dp))
                            AsyncImage(model = bubblePhoto.value.url, contentDescription = "Image", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(120.dp))
            }
        }
    }
}