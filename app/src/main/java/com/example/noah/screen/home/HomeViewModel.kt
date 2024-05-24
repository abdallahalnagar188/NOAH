package com.example.noah.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.getValue

class HomeViewModel : ViewModel() {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("NoahDoor")
    init {
        setupRealTimeLisner()
    }
    private fun setupRealTimeLisner() {
        myRef.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {

                Log.e("onChildAdded", snapshot.getValue().toString())
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                Log.e("onChildChanged", snapshot.getValue().toString())
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }

    fun push(addFingerPrint: Boolean) {
        val id = myRef.push().key
        val newUser = User(
            addFingerPrint,
            true,
            "",
            true,
            "",
            true,
            true)
        if (id != null) {
            myRef.child(id).setValue(newUser).addOnSuccessListener {
                Log.e("OnSuccess","suc")
            }.addOnFailureListener {
                Log.e("OnFiler","fail")
            }
        }
    }
}