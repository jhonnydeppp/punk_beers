package com.jhonny.punkbeer.domain.usecases

import com.jhonny.punkbeer.data.Result
import com.jhonny.punkbeer.data.entities.beer.BeerDataSource
import com.jhonny.punkbeer.di.IoDispatcher
import com.jhonny.punkbeer.domain.base.SuspendMapperUseCase
import com.jhonny.punkbeer.domain.entities.Beers
import com.jhonny.punkbeer.domain.usecases.mapper.BeerMapper
import com.jhonny.punkbeer.presenter.entities.BeersPresentation
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetBeersUseCase @Inject constructor(
    private val beerDataSource: BeerDataSource,
    @IoDispatcher dispatcher: CoroutineDispatcher,
    mapper: BeerMapper
) : SuspendMapperUseCase<Int, BeersPresentation, Beers?>(dispatcher, mapper) {

    override suspend fun execute(parameters: Int): Result<Beers?> {
        return beerDataSource.getBeers(parameters)
    }

}