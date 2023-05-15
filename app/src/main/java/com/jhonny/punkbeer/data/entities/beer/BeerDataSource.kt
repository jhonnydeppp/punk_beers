package com.jhonny.punkbeer.data.entities.beer

import com.jhonny.punkbeer.data.Result
import com.jhonny.punkbeer.domain.entities.Beers

interface BeerDataSource {
    suspend fun getBeers(page: Int): Result<Beers>

    suspend fun getBeersByName(name: String): Result<Beers>

}