package com.example.amphibiansapp

import android.app.Application
import com.example.amphibiansapp.datalayer.AppContainer
import com.example.amphibiansapp.datalayer.DefaultAppContainer

class AmphibianApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}