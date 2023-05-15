package com.jhonny.punkbeer.data.entities.beer.remote

import com.jhonny.punkbeer.data.entities.beer.service.BeerService
import com.jhonny.punkbeer.data.util.BaseRemoteDataSource
import javax.inject.Inject

class BeerRemoteDataSourceImpl @Inject constructor(private val service: BeerService) :
    BeerRemoteDataSource, BaseRemoteDataSource() {

    override suspend fun getBeers(page: Int) = getResult {
        service.getBeers(page)
    }

    override suspend fun getBeersByName(name: String) = getResult {
        service.getBeersByName(name)
    }

}

