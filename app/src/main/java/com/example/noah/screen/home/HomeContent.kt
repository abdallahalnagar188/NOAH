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
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.noah.R
import com.example.noah.screen.buttons.ButtonDef
import com.example.noah.view_model.HomeViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeContent(navController: NavController) {
    val vm: HomeViewModel = remember { HomeViewModel() }


    /*   val database = FirebaseDatabase.getInstance().reference
       val myRef1 = database.child("NoahDoor").child("LastFingerUser")

       // Fetch data from Firebase Realtime Database
       myRef1.addValueEventListener(object : ValueEventListener {
           override fun onDataChange(dataSnapshot: DataSnapshot) {
               // Get string value from snapshot
               lastFingerUser = dataSnapshot.getValue(String::class.java) ?: "No"
           }

           override fun onCancelled(databaseError: DatabaseError) {
               // Handle error
               lastFingerUser = "Failed to load data"
           }
       })*/


    Scaffold {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painterResource(id = R.drawable.bg),
                    contentScale = ContentScale.FillBounds
                ),
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
                ButtonDef(icon = R.drawable.ic_finger)
                ButtonDef(icon = R.drawable.ic_delete)
            }
            Spacer(modifier = Modifier.height(50.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .padding(top = 4.dp)
            ) {
                ButtonDef(icon = R.drawable.unlock)
                ButtonDef(icon = R.drawable.fingerprint)
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
            ) {
                ButtonDef(icon = R.drawable.wifi)

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