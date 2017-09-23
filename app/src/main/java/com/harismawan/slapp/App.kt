package com.harismawan.slapp

import android.app.Application
import com.harismawan.slapp.util.Utils

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Utils.overrideFont(applicationContext, "SERIF", "fonts/montserrat_regular.ttf");
    }
}