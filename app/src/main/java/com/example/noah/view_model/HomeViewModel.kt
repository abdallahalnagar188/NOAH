package com.example.noah.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    // add last finger user
    private val _lastFingerUser = MutableStateFlow<String>("")
    val lastFingerUser: StateFlow<String> = _lastFingerUser

    // add door finger users
    private val _doorFingerUsers = MutableStateFlow<String>("")
    val doorFingerUsers :StateFlow<String> = _doorFingerUsers

    //add wifi order
    private val _booleanValue = MutableLiveData<Boolean>()
    val booleanValue: LiveData<Boolean> get() = _booleanValue

    private val myRef1 = database.reference.child("NoahDoor").child("LastFingerUser")
    private val myRef2 = database.reference.child("NoahDoor").child("DoorFingerUsers")
    private val myRefWifi = database.reference.child("NoahDoor").child("WiFiOrder")

    init {
        setupRealTimeListener()
        readLastFingerUser()
        readDoorFingerUsers()
        fetchBooleanValue()
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

    private fun fetchBooleanValue() {
        myRefWifi.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                _booleanValue.value = dataSnapshot.getValue(Boolean::class.java) ?: false
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle possible errors.
            }
        })
    }
}
