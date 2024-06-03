package com.example.noah.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.noah.R
import com.example.noah.view_model.HomeViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeContent(navController: NavController) {
    val vm: HomeViewModel = remember { HomeViewModel() }


    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.color_app))
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HomeTopBar()
                CardItem(name = "Wifi Order", onClick = {
                    if (it) {
                        vm.updateWifiOrder(true)
                    } else (vm.updateWifiOrder(false))
                })
                CardItem(
                    name = "Add Finger Print",
                    onClick = {
                        if (it) {
                            vm.updateAddFingerPrint(true)
                        } else (vm.updateAddFingerPrint(false))
                })
                CardItem(
                    name = "Delete Finger User ",
                    onClick = {
                    if (it) {
                        vm.updateDeleteFingerUser(true)
                    } else (vm.updateDeleteFingerUser(false))
                })
                CardItem(
                    name = "Finger Mode",
                    onClick = {
                    if (it) {
                        vm.updateFingerMode(true)
                    } else (vm.updateFingerMode(false))
                })
                CardItem(
                    name = "Un Lock",
                    onClick = {
                    if (it) {
                        vm.updateUnLock(true)
                    } else (vm.updateUnLock(false))
                })

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    CardSmallItem(
                        modifier = Modifier
                            .weight(0.5f)
                            .height(60.dp)
                            .padding(horizontal = 6.dp),
                        name = "Last User",
                        onClick = {},
                        num = "1"
                    )
                    CardSmallItem(
                        modifier = Modifier
                            .weight(0.5f)
                            .height(60.dp)
                            .padding(horizontal = 6.dp),
                        name = "Users",
                        onClick = {},
                        num = "200"
                    )
                }
                AboutUsButton(onClick = {
                    navController.navigate("aboutUs")
                }
                )
            }
        }
    }
}