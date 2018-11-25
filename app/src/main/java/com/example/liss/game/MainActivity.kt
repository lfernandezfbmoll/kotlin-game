package com.example.liss.game

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : GameActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkUserName()
    }

    private fun checkUserName() {
        val settings : SharedPreferences = getSharedPreferences(GAME_PREFERENCES, MODE_PRIVATE)
        val prefEditor = settings.edit()
        if (!settings.contains("UserName"))
            prefEditor.putString("UserName", "Default")
        if(settings.contains("LastAccess"))
            Log.d("Prev access", settings.getString("LastAccess", "Default"))
        prefEditor.putString("LastAccess", Calendar.getInstance().time.toString())
        prefEditor.apply()

        Timer("SettingUp", false).schedule(5000) {
            openMenu()
        }
    }

    private fun openMenu() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
    }
}
