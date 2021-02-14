package com.rafaelperezolm.cinepolis.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.rafaelperezolm.cinepolis.ui.login.LoginActivity

//Manages the shared preferences for the user session
class SessionManager(
    private var context: Context,
    var sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE),
    @SuppressLint("CommitPrefEdits") private var sharedPreferencesEditor: SharedPreferences.Editor = sharedPreferences.edit()) {

    companion object {
        private const val PRIVATE_MODE = 0
        private const val PREF_NAME = "Logged"
        private const val IS_LOGIN = "IsLoggedIn"
        private const val KEY_EMAIL = "email"
        private const val KEY_TOKEN = "token"
    }

    fun createLoginSession(email: String, token: String) {
        // Storing login value as TRUE
        sharedPreferencesEditor.putBoolean(IS_LOGIN, true)
        // Storing email in pref
        sharedPreferencesEditor.putString(KEY_EMAIL, email)
        // Storing token in pref
        sharedPreferencesEditor.putString(KEY_TOKEN, token)
        // commit changes
        sharedPreferencesEditor.commit()
    }

    fun checkLogin() {
        // Check login status
        if (!this.isLoggedIn()) {
            // user is not logged in redirect him to Login Activity
            val i = Intent(context, LoginActivity::class.java)
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            // Add new Flag to start new Activity
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            // Staring Login Activity
            context.startActivity(i)
        }
    }

    fun logoutUser() {
        // Clearing all data from Shared Preferences
        sharedPreferencesEditor.clear()
        sharedPreferencesEditor.commit()
        // After logout redirect user to Loing Activity
        val i = Intent(context, LoginActivity::class.java)
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        // Add new Flag to start new Activity
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        // Staring Login Activity
        context.startActivity(i)
    }

    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(IS_LOGIN, false)
    }

}