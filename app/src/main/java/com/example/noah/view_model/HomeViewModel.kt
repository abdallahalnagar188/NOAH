package com.example.noah.view_model

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeViewModel : ViewModel() {
    private val database = FirebaseDatabase.getInstance()
    private val myRef = database.getReference()
    val lastFingerUser = mutableStateOf("Loading..")

    init {
        setupRealTimeListener()
    }

    private fun setupRealTimeListener() {
        myRef.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {

                Log.e("onChildAdded", snapshot.value.toString())
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                Log.e("onChildChanged", snapshot.value.toString())
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                Log.e("onChildRemoved", snapshot.value.toString())
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                Log.e("onChildMoved", snapshot.value.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("onCancelled", error.toString())
            }

        }
        )
    }



    fun updateWifiOrder (wifiOrder: Boolean) {
        myRef.child("NoahDoor").child("WiFiOrder").setValue(wifiOrder)
    }
    fun updateAddFingerPrint (addFingerPrint: Boolean) {
        myRef.child("NoahDoor").child("AddFingerUser").setValue(addFingerPrint)
    }
    fun updateDeleteFingerUser(deleteFingerUser:Boolean){
        myRef.child("NoahDoor").child("DeleteFingerUsers").setValue(deleteFingerUser)
    }
    fun updateFingerMode(fingerMode:Boolean){
        myRef.child("NoahDoor").child("FingerMode").setValue(fingerMode)
    }
    fun updateUnLock(unlock:Boolean){
        myRef.child("NoahDoor").child("Unlock").setValue(unlock)
    }

    fun readLastUser() {
        myRef.child("NoahDoor").child("LastFingerUser")
        // Fetch data from Firebase Realtime Database
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get string value from snapshot
                lastFingerUser.value = dataSnapshot.getValue(String::class.java) ?: "No"
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle error
                lastFingerUser.value = "Failed to load data"
            }
        })
    }
}