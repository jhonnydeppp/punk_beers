package com.jhonny.punkbeer.di

import com.jhonny.punkbeer.data.entities.beer.mapper.BeerMapper
import com.jhonny.punkbeer.data.entities.beer.mapper.BeerMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MapperModule {

    @Singleton
    @Provides
    fun beerMapperProvider(): BeerMapper = BeerMapperImpl()
}