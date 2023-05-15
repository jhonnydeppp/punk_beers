package com.jhonny.punkbeer.di

import com.jhonny.punkbeer.data.entities.beer.BeerDataSource
import com.jhonny.punkbeer.data.entities.beer.BeerRepository
import com.jhonny.punkbeer.data.entities.beer.mapper.BeerMapper
import com.jhonny.punkbeer.data.entities.beer.remote.BeerRemoteDataSource
import com.jhonny.punkbeer.data.entities.beer.remote.BeerRemoteDataSourceImpl
import com.jhonny.punkbeer.data.entities.beer.service.BeerService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun beerRepositoryProvider(
        beerRemoteDataSource: BeerRemoteDataSource,
        beerMapper: BeerMapper
    ) = BeerRepository(
        beerRemoteDataSource,
        beerMapper
    )

    @Singleton
    @Provides
    fun beerDataSourceImplProvider(
        beerService: BeerService
    ) = BeerRemoteDataSourceImpl(
        beerService
    )

    @Singleton
    @Provides
    fun beerDataSourceProvider(
        beerRemoteDataSource: BeerRemoteDataSource,
        beerMapper: BeerMapper
    ): BeerDataSource = BeerRepository(
        beerRemoteDataSource,
        beerMapper
    )

    @Singleton
    @Provides
    fun beerRemoteDataSourceProvider(
        beerService: BeerService
    ): BeerRemoteDataSource = BeerRemoteDataSourceImpl(
        beerService
    )

}