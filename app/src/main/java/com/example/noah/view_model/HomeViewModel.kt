package com.example.noah.view_model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val database = FirebaseDatabase.getInstance()
    private val myRef = database.getReference()

    private val _lastFingerUser = MutableStateFlow<String>("Loading...")
    val lastFingerUser: StateFlow<String> = _lastFingerUser

    private val _doorFingerUsers = MutableStateFlow<String>("Loading...")
    val doorFingerUsers :StateFlow<String> = _doorFingerUsers

    private val myRef1 = database.reference.child("NoahDoor").child("LastFingerUser")
    private val myRef2 = database.reference.child("NoahDoor").child("DoorFingerUsers")

    init {
        setupRealTimeListener()
        readLastFingerUser()
        readDoorFingerUsers()
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

    private fun readLastFingerUser() {
        viewModelScope.launch {
            myRef1.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    _lastFingerUser.value = dataSnapshot.getValue(String::class.java) ?: "No data found"
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    _lastFingerUser.value = "Failed to load data"
                }
            })
        }
    }

    private fun readDoorFingerUsers() {
        viewModelScope.launch {
            myRef2.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    _doorFingerUsers.value = dataSnapshot.getValue(String::class.java) ?: "No data found"
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    _doorFingerUsers.value = "Failed to load data"
                }
            })
        }
    }
}
