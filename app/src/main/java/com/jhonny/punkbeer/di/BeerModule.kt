package com.jhonny.punkbeer.di

import com.jhonny.punkbeer.domain.usecases.GetBeersByNameUseCase
import com.jhonny.punkbeer.domain.usecases.GetBeersUseCase
import com.jhonny.punkbeer.presenter.main.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class BeerModule {

    @Provides
    fun beerViewModelProvider(
        getAllBeersUseCase: GetBeersUseCase,
        getBeersByNameUseCase: GetBeersByNameUseCase
    ) = MainViewModel (
        getAllBeersUseCase,
        getBeersByNameUseCase
    )

}