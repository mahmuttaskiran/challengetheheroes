package com.mamudo.challengetheheroes

import android.app.Application
import com.mamudo.challengetheheroes.di.ApplicationGraph
import com.mamudo.challengetheheroes.di.DaggerApplicationGraph


class HeroesApplication : Application() {
    lateinit var applicationGraph: ApplicationGraph
    override fun onCreate() {
        super.onCreate()
        applicationGraph = DaggerApplicationGraph.create()
    }
}