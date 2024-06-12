package com.example.noah.screen.cards


import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noah.R
import com.example.noah.view_model.HomeViewModel

@Composable
fun CardDoor(
    painter: Painter,
    onTClick: (Boolean) -> Unit,
    isLoading: Boolean,
    errorMessage: String?
) {
    val vm: HomeViewModel = remember { HomeViewModel() }
    val lastFingerUser by vm.lastFingerUser.collectAsState()
    val doorFingerUsers by vm.doorFingerUsers.collectAsState()

    Card(
        modifier = Modifier
            .fillMaxHeight(0.45f)
            .padding(horizontal = 30.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Box(contentAlignment = Alignment.Center) {
                IconButton(
                    onClick = { onTClick(true) },
                    modifier = Modifier.size(170.dp)
                ) {
                    Image(
                        painter = painter,
                        contentDescription = "Image Finger",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .size(170.dp)
                    )
                }
                if (isLoading) {
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            if (errorMessage != null) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CardItemOfCont(
                    name = lastFingerUser,
                    lastOrUsers = stringResource(id = R.string.lastUser)
                )
                CardItemOfCont(
                    name = doorFingerUsers,
                    lastOrUsers = stringResource(id = R.string.users)
                )
            }
        }
    }
}

@Composable
fun CardItemOfCont(name: String, lastOrUsers: String) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Card(
            modifier = Modifier
                .height(60.dp)
                .width(80.dp)
                .border(
                    2.5.dp,
                    color = colorResource(id = R.color.coffie2),
                    shape = RoundedCornerShape(20.dp)
                ),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.coffie)
            ),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 7.dp, draggedElevation = 20.dp
            )
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = name,
                    fontSize = 24.sp,
                    fontFamily = FontFamily.Serif,
                    style = TextStyle(color = Color.DarkGray),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        Text(
            text = lastOrUsers,
            fontSize = 22.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.Serif,
            style = TextStyle(color = Color.White),
            modifier = Modifier
                .padding(top = 4.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

