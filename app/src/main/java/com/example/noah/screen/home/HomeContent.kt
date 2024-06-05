package com.example.noah.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.noah.view_model.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
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
                .background(color = Color.DarkGray),
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
                name = "Delete Finger User",
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

            AboutUsButton(onClick = {
                navController.navigate("aboutUs")
            })
        }

    }
}