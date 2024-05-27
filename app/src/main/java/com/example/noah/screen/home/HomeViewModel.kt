package com.example.noah.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase

class HomeViewModel : ViewModel() {
    private val database = FirebaseDatabase.getInstance()
    private val myRef = database.getReference("NoahDoor")
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
}
//
//        fun push(addFingerPrint: Boolean) {
//            val id = myRef.push().key
//            val newUser = User(
//                addFingerPrint,
//                DeleteFingerUsers = true,
//                DoorFingerUsers = "200",
//                FingerMode = true,
//                LastFingerUser = "2",
//                Unlock = true,
//                WifiOrder = false
//            )
//            if (id != null) {
//                myRef.child(id).setValue(newUser).addOnSuccessListener {
//                    Log.e("OnSuccess","suc")
//                }.addOnFailureListener {
//                    Log.e("OnFiler","fail")
//                }
//            }
//        }
//
//    }