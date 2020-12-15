package com.mamudo.challengetheheroes.di

import com.mamudo.challengetheheroes.api.MarvelApiTest
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface TestApplicationGraph : ApplicationGraph {
    fun inject(test: MarvelApiTest)
}
