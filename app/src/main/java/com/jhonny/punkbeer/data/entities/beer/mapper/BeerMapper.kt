package com.jhonny.punkbeer.data.entities.beer.mapper

import com.jhonny.punkbeer.data.entities.beer.entities.BeerResponseItem
import com.jhonny.punkbeer.domain.entities.Beers


interface BeerMapper {
    fun responseToDomain(info: List<BeerResponseItem?>?): Beers
}
