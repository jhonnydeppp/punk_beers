package com.jhonny.punkbeer.domain.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Beers(
    var list: List<BeerDomain?>? = null
) : Parcelable

@Parcelize
data class BeerDomain(
    @field:SerializedName("abv")
    val abv: Float? = null,

    @field:SerializedName("image_url")
    val imageUrl: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("ph")
    val ph: Float? = null,
): Parcelable
