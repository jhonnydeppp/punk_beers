package com.jhonny.punkbeer.data.entities.beer.mapper


import com.jhonny.punkbeer.data.entities.beer.entities.BeerResponseItem
import com.jhonny.punkbeer.domain.entities.BeerDomain
import com.jhonny.punkbeer.domain.entities.Beers
import javax.inject.Inject

class BeerMapperImpl @Inject constructor() : BeerMapper {

    override fun responseToDomain(info: List<BeerResponseItem?>?) =
        Beers(
            info?.map {
            BeerDomain(
                abv = it?.abv,
                imageUrl = it?.imageUrl,
                description = it?.description,
                name = it?.name,
                ph = it?.ph
            )
        } ?: emptyList()
        )

}
