package com.jhonny.punkbeer.data.entities.beer

import com.jhonny.punkbeer.data.entities.beer.mapper.BeerMapper
import com.jhonny.punkbeer.data.entities.beer.remote.BeerRemoteDataSource
import com.jhonny.punkbeer.data.map
import javax.inject.Inject

class BeerRepository @Inject constructor(private val beerRemoteDataSource: BeerRemoteDataSource,
                                         private val beerMapper: BeerMapper
): BeerDataSource {

    override suspend fun getBeers(page: Int) = beerRemoteDataSource.getBeers(page)
        .map(beerMapper::responseToDomain)

    override suspend fun getBeersByName(name: String) = beerRemoteDataSource.getBeersByName(name)
        .map(beerMapper::responseToDomain)

}