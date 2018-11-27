package com.example.liss.game

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import java.util.*
import kotlin.concurrent.schedule


class MainActivity : GameActivity() {
    private lateinit var logo1 : TextView
    private lateinit var logo2 : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        logo1 = findViewById<View>(R.id.TextViewTopTitle) as TextView
        logo2 = findViewById<View>(R.id.TextViewBottomTitle) as TextView

        applyAnimation()
        checkUserName()
    }

    private fun applyAnimation() {
        val fade1 = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        logo1.startAnimation(fade1)
        val fade2 = AnimationUtils.loadAnimation(this, R.anim.fade_in2)
        logo2.startAnimation(fade2)
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

    override fun onPause() {
        super.onPause()

        logo1.clearAnimation()
        logo2.clearAnimation()
    }
}
