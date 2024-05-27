package com.example.noah.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase

class HomeViewModel : ViewModel() {
    private val database = FirebaseDatabase.getInstance()
    private val myRef = database.getReference()
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

            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
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
}