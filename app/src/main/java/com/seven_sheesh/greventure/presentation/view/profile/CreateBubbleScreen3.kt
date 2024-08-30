package com.seven_sheesh.greventure.presentation.view.profile

import android.content.Context
import android.widget.Toast
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.seven_sheesh.greventure.R
import com.seven_sheesh.greventure.domain.model.Bubble
import com.seven_sheesh.greventure.domain.model.BubblePhoto
import com.seven_sheesh.greventure.domain.model.BubbleSocialMedia
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme
import com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj.HomeNavObj
import com.seven_sheesh.greventure.presentation.viewmodel.CreateBubbleViewModel
import com.seven_sheesh.greventure.presentation.viewmodel.NavbarViewModel
import com.seven_sheesh.greventure.ui.viewmodel.BubbleViewModel
import kotlinx.coroutines.launch

@Composable
@Preview
fun CreateBubbleScreen3(
    homeNavController: NavController = rememberNavController(),
    navbarViewModel: NavbarViewModel = hiltViewModel(),
    createBubbleViewModel: CreateBubbleViewModel = hiltViewModel(),
){
    navbarViewModel.setPageState(3)
    val bubbleViewModel = hiltViewModel<BubbleViewModel>()
    val message = bubbleViewModel.messageState.collectAsState()
    val bubble = createBubbleViewModel.bubbleState.collectAsState()
    val bubblePhoto = createBubbleViewModel.bubblePhoto.collectAsState()
    val bubbleSocialMedia = createBubbleViewModel.bubbleSocialMedia.collectAsState()
    val context = LocalContext.current

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            createBubbleViewModel.updateBubblePhoto(bubblePhoto.value.copy(url = uri.toString()))
        }
    )

    LaunchedEffect(message.value) {
        if (message.value.isNotEmpty()) {
            Toast.makeText(context, message.value, Toast.LENGTH_SHORT).show()
        }

        if(message.value.contains("successfully")){
            homeNavController.navigate(HomeNavObj.YourBubbleScreen.route)
        }
    }

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
                            homeNavController.navigate(HomeNavObj.CreateBubble2.route)
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
                    Image(painter = painterResource(id = R.drawable.create_bubble_3), contentDescription = "step 1", modifier = Modifier.height(72.dp))
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

                        Spacer(modifier = Modifier.height(24.dp))
                        HorizontalDivider()
                        Spacer(modifier = Modifier.height(16.dp))

                        Card(modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                            colors = CardDefaults.cardColors(GreventureScheme.Primary.color),
                            shape = RoundedCornerShape(50.dp)
                        ) {
                            Box(modifier = Modifier
                                .fillMaxSize()
                                .clickable {
                                    handleBubbleData(
                                        bubble = bubble.value,
                                        bubblePhoto = bubblePhoto.value,
                                        bubbleSocialMedia = listOf(
                                            bubbleSocialMedia.value.first,
                                            bubbleSocialMedia.value.second,
                                            bubbleSocialMedia.value.third
                                        ),
                                        bubbleViewModel = bubbleViewModel,
                                        context = context
                                    )
                                }, contentAlignment = Alignment.Center){
                                Text(text = "Lanjut", color = GreventureScheme.White.color, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                            }
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

fun handleBubbleData(
    bubble: Bubble,
    bubblePhoto: BubblePhoto,
    bubbleSocialMedia: List<BubbleSocialMedia>,
    bubbleViewModel: BubbleViewModel,
    context: Context
) {
    if (bubble.isComplete() && bubblePhoto.isComplete()) {
        if (bubbleSocialMedia.any { it.isComplete() }) {
            bubbleViewModel.upsertBubble(bubble)
            bubbleViewModel.upsertBubblePhoto(bubblePhoto)

            bubbleSocialMedia.filter { it.isComplete() }.forEach {
                bubbleViewModel.upsertBubbleSocialMedia(it)
            }
        } else {
            Toast.makeText(context, "Lengkapi media sosial terlebih dahulu", Toast.LENGTH_SHORT).show()
        }
    } else {
        Toast.makeText(context, "Lengkapi data terlebih dahulu", Toast.LENGTH_SHORT).show()
    }
}
