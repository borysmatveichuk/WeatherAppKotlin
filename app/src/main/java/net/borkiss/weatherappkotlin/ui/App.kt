package net.borkiss.weatherappkotlin.ui

import android.app.Application
import net.borkiss.weatherappkotlin.ui.utils.DelegatesExt

class App : Application() {

    companion object {
        var instance: App by DelegatesExt.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}