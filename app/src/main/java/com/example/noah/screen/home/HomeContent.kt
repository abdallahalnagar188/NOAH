package com.example.noah.screen.home

import android.annotation.SuppressLint
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
    val wifiOrder by vm.wifiOrder.observeAsState(false)
    val bg = if (wifiOrder) {
        painterResource(id = R.drawable.iconapptwo)
    } else {
        painterResource(id = R.drawable.iconapp)
    }

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .paint(
                    painter = painterResource(id = R.drawable.bgcolor),
                    contentScale = ContentScale.FillBounds
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HomeTopBar(navController)
            CardDoor(painter = bg,
                onTClick = {
                    Repo(activity).wifiOrderToShard(true)
                    vm.updateWifiOrder(true)
                })
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .padding(top = 26.dp)
            ) {
                ButtonDef(
                    icon = R.drawable.ic_finger,
                    namOfButton = stringResource(id = R.string.addFingerPrint),
                    onClick = {
                        Repo(activity).addFingerToShard(true)
                        vm.updateWifiOrder(true)
                        vm.observeWiFiOrderAndUpdateAddFingerUser()
                    }
                )
                ButtonDef(
                    icon = R.drawable.ic_delete,
                    namOfButton = stringResource(id = R.string.deleteFingerUsers),
                    onClick = {
                        Repo(activity).deleteUsersToShard(true)
                        vm.updateWifiOrder(true)
                        vm.observeWiFiOrderAndUpdateDeleteFingerUser()
                    }

                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .padding(top = 4.dp)
            ) {
                ButtonDef(
                    icon = R.drawable.unlock,
                    namOfButton = stringResource(id = R.string.unLock),
                    onClick = {

                        Repo(activity).unlockToShard(true)
                        vm.updateWifiOrder(true)
                        vm.observeWiFiOrderAndUpdateUnlock()
                    },
                )
                ButtonDef(
                    icon = R.drawable.fingerprint,
                    namOfButton = stringResource(id = R.string.fingerMode),
                    onClick = {
                        Repo(activity).fingerModeToShard(true)
                        vm.updateWifiOrder(true)
                        vm.observeWiFiOrderAndUpdateFingerMode()
                    }
                )
            }
        }
    }
}
