package com.jhonny.punkbeer.data.entities.beer.remote

import com.jhonny.punkbeer.data.Result
import com.jhonny.punkbeer.data.entities.beer.entities.BeerResponseItem

interface BeerRemoteDataSource {

    suspend fun getBeers(page: Int = 1): Result<List<BeerResponseItem?>?>

    suspend fun getBeersByName(name: String = ""): Result<List<BeerResponseItem?>?>
}