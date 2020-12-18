package com.mamudo.challengetheheroes.di

import com.mamudo.challengetheheroes.api.MarvelApi
import com.mamudo.challengetheheroes.ui.activities.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ApplicationGraph {
    fun inject(a: MainActivity)
    fun api(): MarvelApi
}
