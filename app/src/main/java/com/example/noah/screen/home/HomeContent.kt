package com.example.noah.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.noah.R
import com.example.noah.screen.buttons.ButtonDef
import com.example.noah.screen.cards.CardDoor
import com.example.noah.view_model.HomeViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeContent(navController: NavController) {
    val vm: HomeViewModel = remember { HomeViewModel() }
    val isConnected = remember { mutableStateOf(false) }


    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize().verticalScroll(rememberScrollState())
                .background(color = colorResource(id = R.color.green)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HomeTopBar(navController)
            CardDoor()
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .padding(top = 26.dp)
            ) {
                ButtonDef(icon = R.drawable.ic_finger,
                    stringResource(id = R.string.addFingerPrint),
                    onClick = {
                        if (it) {
                            vm.updateAddFingerPrint(true)
                        } else (vm.updateAddFingerPrint(false))
                    })
                ButtonDef(
                    icon = R.drawable.ic_delete,
                    stringResource(id = R.string.deleteFingerUsers),
                    onClick = {
                        if (it) {
                            vm.updateDeleteFingerUser(true)
                        } else (vm.updateDeleteFingerUser(false))
                    }
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .padding(top = 4.dp)
            ) {
                ButtonDef(
                    icon = R.drawable.unlock,
                    stringResource(id = R.string.unLock),
                    onClick = {
                        if (it) {
                            vm.updateUnLock(true)
                        } else (vm.updateUnLock(false))
                    })
                ButtonDef(
                    icon = R.drawable.fingerprint,
                    stringResource(id = R.string.fingerMode),
                    onClick = {
                        if (it) {
                            vm.updateFingerMode(true)
                        } else (vm.updateFingerMode(false))
                    })
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
            ) {
                ButtonDef(icon = R.drawable.wifi, stringResource(id = R.string.wifiOrder),
                    onClick = {
                        if (it) {
                            vm.updateWifiOrder(true)
                        } else (vm.updateWifiOrder(false))
                    })

            }



/*            CardItem(name = "Wifi Order", onClick = {
                if (it) {
                    vm.updateWifiOrder(true)
                } else (vm.updateWifiOrder(false))
            })
            CardItem(
                name = stringResource(id = R.string.addFingerPrint),
                onClick = {
                    if (it) {
                        vm.updateAddFingerPrint(true)
                    } else (vm.updateAddFingerPrint(false))
                })
            CardItem(
                name = stringResource(id = R.string.deleteFingerUsers),
                onClick = {
                    if (it) {
                        vm.updateDeleteFingerUser(true)
                    } else (vm.updateDeleteFingerUser(false))
                })
            CardItem(
                name = stringResource(id = R.string.fingerMode),
                onClick = {
                    if (it) {
                        vm.updateFingerMode(true)
                    } else (vm.updateFingerMode(false))
                })
            CardItem(
                name = stringResource(id = R.string.unLock),
                onClick = {
                    if (it) {
                        vm.updateUnLock(true)
                    } else (vm.updateUnLock(false))
                })*/

//            AboutUsButton(onClick = {
//                navController.navigate("aboutUs")
//            })
        }
    }
}