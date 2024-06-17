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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeContent(navController: NavController) {
    val vm: HomeViewModel = remember { HomeViewModel() }
    val activity = LocalContext.current as MainActivity
    val wifiOrder by vm.wifiOrder.observeAsState(false)

    val isCardDoorLoading = remember { mutableStateOf(false) }
    val cardDoorError = remember { mutableStateOf<String?>(null) }

    val isAddFingerLoading = remember { mutableStateOf(false) }
    val addFingerError = remember { mutableStateOf<String?>(null) }

    val isDeleteUsersLoading = remember { mutableStateOf(false) }
    val deleteUsersError = remember { mutableStateOf<String?>(null) }

    val isUnlockLoading = remember { mutableStateOf(false) }
    val unlockError = remember { mutableStateOf<String?>(null) }

    val isFingerModeLoading = remember { mutableStateOf(false) }
    val fingerModeError = remember { mutableStateOf<String?>(null) }

    val coroutineScope = rememberCoroutineScope()

    val bg = if (wifiOrder) {
        painterResource(id = R.drawable.iconapptwo)
    } else {
        painterResource(id = R.drawable.iconapp)
    }

    LaunchedEffect(wifiOrder) {
        if (!wifiOrder) {
            isCardDoorLoading.value = false
            isAddFingerLoading.value = false
            isDeleteUsersLoading.value = false
            isUnlockLoading.value = false
            isFingerModeLoading.value = false
        }
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
            CardDoor(
                painter = bg,
                onTClick = {
                    isCardDoorLoading.value = true
                    cardDoorError.value = null
                    Repo(activity).wifiOrderToShard(true)
                    vm.updateWifiOrder(true)

                    // Start timeout
                    coroutineScope.launch {
                        delay(30000)
                        if (isCardDoorLoading.value) {
                            isCardDoorLoading.value = false
                            cardDoorError.value = "Failed to connect"
                        }
                    }
                },
                isLoading = isCardDoorLoading.value,
                errorMessage = cardDoorError.value
            )
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
                        isAddFingerLoading.value = true
                        addFingerError.value = null
                        Repo(activity).addFingerToShard(true)
                        vm.updateWifiOrder(true)
                        vm.observeWiFiOrderAndUpdateAddFingerUser()

                        coroutineScope.launch {
                            delay(30000)
                            if (isAddFingerLoading.value) {
                                isAddFingerLoading.value = false
                                addFingerError.value = "Failed to connect"
                            }
                        }
                    },
                    isLoading = isAddFingerLoading.value,
                    errorMessage = addFingerError.value
                )
                ButtonDef(
                    icon = R.drawable.ic_delete,
                    namOfButton = stringResource(id = R.string.deleteFingerUsers),
                    onClick = {
                        isDeleteUsersLoading.value = true
                        deleteUsersError.value = null
                        Repo(activity).deleteUsersToShard(true)
                        vm.updateWifiOrder(true)
                        vm.observeWiFiOrderAndUpdateDeleteFingerUser()
                        coroutineScope.launch {
                            delay(30000)
                            if (isDeleteUsersLoading.value) {
                                isDeleteUsersLoading.value = false
                                deleteUsersError.value = "Failed to connect"
                            }
                        }
                    },
                    isLoading = isDeleteUsersLoading.value,
                    errorMessage = deleteUsersError.value
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
                        isUnlockLoading.value = true
                        unlockError.value = null
                        Repo(activity).unlockToShard(true)
                        vm.updateWifiOrder(true)
                        vm.observeWiFiOrderAndUpdateUnlock()

                        coroutineScope.launch {
                            delay(30000)
                            if (isUnlockLoading.value) {
                                isUnlockLoading.value = false
                                unlockError.value = "Failed to connect"
                            }
                        }
                    },
                    isLoading = isUnlockLoading.value,
                    errorMessage = unlockError.value
                )
                ButtonDef(
                    icon = R.drawable.fingerprint,
                    namOfButton = stringResource(id = R.string.fingerMode),
                    onClick = {
                        isFingerModeLoading.value = true
                        fingerModeError.value = null
                        Repo(activity).fingerModeToShard(true)
                        vm.updateWifiOrder(true)
                        vm.observeWiFiOrderAndUpdateFingerMode()
                        coroutineScope.launch {
                            delay(30000)
                            if (isFingerModeLoading.value) {
                                isFingerModeLoading.value = false
                                fingerModeError.value = "Failed to connect"
                            }
                        }
                    },
                    isLoading = isFingerModeLoading.value,
                    errorMessage = fingerModeError.value
                )
            }
        }
    }
}

