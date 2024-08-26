package com.seven_sheesh.greventure.presentation.view.boarding

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.seven_sheesh.greventure.R
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme
import com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj.ParentNavObj
import com.seven_sheesh.greventure.utils.loadProgress
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview
fun BoardingScreen2(
    parentNavController: NavController = rememberNavController()
){
    val currentProgress = remember { mutableStateOf(0f) }
    LaunchedEffect(Unit) {
        loadProgress {
            currentProgress.value = it
        }
        parentNavController.navigate(ParentNavObj.BoardingScreen3.route)
    }

    Scaffold(
        containerColor = GreventureScheme.White.color,
        contentColor = GreventureScheme.White.color,
        bottomBar = {
            Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp)){
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier)
                    Button(onClick = {
                        parentNavController.navigate(ParentNavObj.BoardingScreen3.route)
                    },
                        colors = ButtonDefaults.buttonColors(GreventureScheme.Primary.color),
                        modifier = Modifier.height(48.dp).width(150.dp),
                    ) {
                        Text(text = "Lanjut", color = GreventureScheme.White.color, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                    }
                }
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth(), color = GreventureScheme.Primary.color, trackColor = GreventureScheme.SoftGray.color, progress = currentProgress.value)
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(id = R.drawable.boarding_2), contentDescription = "Icon", modifier = Modifier.size(260.dp))
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "Jelajahi Kotamu", color = GreventureScheme.Black.color, fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "Lihat berbagai event hijau menarik \ndi sekitarmu", color = GreventureScheme.Black.color, textAlign = TextAlign.Center, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(64.dp))
        }
    }
}