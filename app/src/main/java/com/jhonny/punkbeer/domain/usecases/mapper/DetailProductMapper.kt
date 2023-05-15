package com.jhonny.punkbeer.domain.usecases.mapper


import com.jhonny.punkbeer.domain.entities.Beers
import com.jhonny.punkbeer.domain.mapper.Mapper
import com.jhonny.punkbeer.presenter.entities.BeerPresentation
import com.jhonny.punkbeer.presenter.entities.BeersPresentation
import javax.inject.Inject

class BeerMapper @Inject constructor() : Mapper<Beers?, BeersPresentation>() {

    override fun map(info: Beers?): BeersPresentation =
        BeersPresentation(
            info?.list?.map {
                BeerPresentation(
                    abv = it?.abv,
                    imageUrl = it?.imageUrl,
                    description = it?.description,
                    name = it?.name,
                    ph = it?.ph
                )
            } ?: emptyList()
        )

}
