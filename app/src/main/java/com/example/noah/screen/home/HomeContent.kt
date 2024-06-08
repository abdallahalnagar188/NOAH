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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.noah.R
import com.example.noah.screen.buttons.ButtonDef
import com.example.noah.screen.cards.CardDoor
import com.example.noah.view_model.HomeViewModel
import com.example.noah.view_model.Repo

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeContent(navController: NavController) {
    val vm: HomeViewModel = remember { HomeViewModel() }
    val activity = LocalContext.current as MainActivity
    val isConnected = rememberSaveable { mutableStateOf(false) }

    val bg = if (isConnected.value) {
        painterResource(id = R.drawable.iconapp)
    } else {
        painterResource(id = R.drawable.iconapptwo)
    }



    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(color = colorResource(id = R.color.green)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HomeTopBar(navController)
            CardDoor(painter = bg)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .padding(top = 26.dp)
            ) {
                ButtonDef(
                    icon = R.drawable.ic_finger,
                    stringResource(id = R.string.addFingerPrint),
                    onClick = {
                        if (it) {
                            Repo(activity).addFingerToShard(true)
                            vm.updateAddFingerPrint(true)
                        } else {
                            Repo(activity).addFingerToShard(false)
                            vm.updateAddFingerPrint(false)
                        }

                    }, boolean = Repo(activity).get("addFinger")
                )
                ButtonDef(
                    icon = R.drawable.ic_delete,
                    stringResource(id = R.string.deleteFingerUsers),
                    onClick = {

                        if (it) {
                            Repo(activity).deleteUsersToShard(true)
                            vm.updateDeleteFingerUser(true)
                        } else {
                            Repo(activity).deleteUsersToShard(false)
                            vm.updateDeleteFingerUser(false)
                        }

                    }, boolean = Repo(activity).get("deleteUsers")
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
                            Repo(activity).unlockToShard(true)
                            vm.updateUnLock(true)
                        } else {
                            Repo(activity).unlockToShard(false)
                            vm.updateUnLock(false)
                        }
                    }, boolean = Repo(activity).get("unlock")
                )
                ButtonDef(
                    icon = R.drawable.fingerprint,
                    stringResource(id = R.string.fingerMode),
                    onClick = {
                        if (it) {
                            Repo(activity).fingerModeToShard(true)
                            vm.updateFingerMode(true)
                        } else {
                            Repo(activity).fingerModeToShard(false)
                            vm.updateFingerMode(false)
                        }
                    }, boolean = Repo(activity).get("fingerMode")
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
            ) {
                ButtonDef(
                    icon = R.drawable.wifi,
                    stringResource(id = R.string.wifiOrder),
                    onClick = {
                        if (it) {
                            Repo(activity).wifiOrderToShard(true)
                            vm.updateWifiOrder(true)
                        } else {
                            Repo(activity).wifiOrderToShard(false)
                            vm.updateWifiOrder(false)
                        }
                        isConnected.value = !isConnected.value
                    }, boolean = Repo(activity).get("wifiOrder"))

            }
        }
    }
}