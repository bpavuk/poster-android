package com.bpavuk.posterapp

import android.app.Application
import com.bpavuk.posterapp.data.AppContainer
import com.bpavuk.posterapp.data.FakeAppContainer

class PosterApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = FakeAppContainer()
    }
}