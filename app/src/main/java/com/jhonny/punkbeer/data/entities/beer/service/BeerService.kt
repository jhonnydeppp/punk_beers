package com.jhonny.punkbeer.data.entities.beer.service

import com.jhonny.punkbeer.data.APIConstants.BEER_NAME
import com.jhonny.punkbeer.data.APIConstants.ENDPOINT_BEERS
import com.jhonny.punkbeer.data.APIConstants.PAGE
import com.jhonny.punkbeer.data.APIConstants.PAGE_AMOUNT
import com.jhonny.punkbeer.data.APIConstants.PER_PAGE
import com.jhonny.punkbeer.data.entities.beer.entities.BeerResponseItem
import retrofit2.http.GET
import retrofit2.http.Query

interface BeerService {

    @GET(ENDPOINT_BEERS)
    suspend fun getBeers(
        @Query(PAGE) page: Int,
        @Query(PER_PAGE) perPage: Int = PAGE_AMOUNT
    ): List<BeerResponseItem?>?

    @GET(ENDPOINT_BEERS)
    suspend fun getBeersByName(
        @Query(BEER_NAME) beerName: String): List<BeerResponseItem?>?

}