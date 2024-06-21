package com.example.noah.screen.password

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.launch

class PasswordViewModel(private val context: Context) : ViewModel() {

    private val sharedPreferences = context.getSharedPreferences("password_prefs", Context.MODE_PRIVATE)

    fun isPasswordSet(): Boolean {
        return sharedPreferences.contains("password")
    }

    fun savePassword(password: String) {
        sharedPreferences.edit().putString("password", password).apply()
        // Save password to Firebase Realtime Database
        val database = FirebaseDatabase.getInstance().reference
        database.child("NoahDoor").child("Password").setValue(password)
    }

    private fun getPassword(): String? {
        return sharedPreferences.getString("password", null)
    }

    fun checkPassword(password: String, callback: (Boolean) -> Unit) {
        // Retrieve password from Firebase Realtime Database
        val database = FirebaseDatabase.getInstance().reference
        database.child("NoahDoor").child("Password").get().addOnSuccessListener {
            val storedPassword = it.getValue(String::class.java)
            callback(storedPassword == password)
        }.addOnFailureListener {
            callback(false)
        }
    }

    fun checkPasswordFromSharedPrefs(password: String, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            val storedPassword = getPassword()
            callback(storedPassword == password)
        }
    }
}
