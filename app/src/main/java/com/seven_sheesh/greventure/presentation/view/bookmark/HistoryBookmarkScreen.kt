package com.seven_sheesh.greventure.presentation.view.bookmark

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme
import com.seven_sheesh.greventure.presentation.ui.design_system.PlusJakartaSans
import com.seven_sheesh.greventure.presentation.ui.navigation.nav_obj.HomeNavObj
import com.seven_sheesh.greventure.presentation.ui.widget.bookmark.BookmarkList
import com.seven_sheesh.greventure.presentation.viewmodel.BookmarkViewModel
import com.seven_sheesh.greventure.presentation.viewmodel.HistoryBookmarkViewModel
import com.seven_sheesh.greventure.presentation.viewmodel.NavbarViewModel

@Composable
fun HistoryBookmarkScreen(
    homeNavController: NavController = rememberNavController(),
    navbarViewModel: NavbarViewModel = hiltViewModel(),
    historyBookmarkViewModel: HistoryBookmarkViewModel = hiltViewModel()
) {
    val user = navbarViewModel.user.collectAsState().value.second
    val bookmarks = historyBookmarkViewModel.bookmarks.collectAsState().value.second

    LaunchedEffect(Unit)     {
        historyBookmarkViewModel.loadHistoryBookmarks(user?.id!!)
    }

    navbarViewModel.setPageState(2)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GreventureScheme.White.color)
            .safeDrawingPadding(),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
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
                                homeNavController.navigate(HomeNavObj.BookmarkScreen.route)
                            },
                            tint = GreventureScheme.White.color
                        )
                        Column {
                            Text(
                                text = "History Bookmark",
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

            item{
                BookmarkList(bookmarks = bookmarks, homeNavController = homeNavController)
            }

            item {
                Spacer(modifier = Modifier.height(140.dp))
            }
        }
    }
}