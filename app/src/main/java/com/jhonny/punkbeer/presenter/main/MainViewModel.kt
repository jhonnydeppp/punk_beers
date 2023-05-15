package com.jhonny.punkbeer.presenter.main

import com.jhonny.punkbeer.data.dataOrNull
import com.jhonny.punkbeer.data.getError
import com.jhonny.punkbeer.data.isError
import com.jhonny.punkbeer.data.isSuccess
import com.jhonny.punkbeer.domain.usecases.GetBeersByNameUseCase
import com.jhonny.punkbeer.domain.usecases.GetBeersUseCase
import com.jhonny.punkbeer.extensions.launch
import com.jhonny.punkbeer.presenter.base.BaseViewModel
import com.jhonny.punkbeer.presenter.entities.BeerPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getBeersUseCase: GetBeersUseCase,
    private val getBeersByNameUseCase: GetBeersByNameUseCase
) : BaseViewModel() {
    private val _beer = MutableStateFlow<List<BeerPresentation?>>(emptyList())
    val beer: StateFlow<List<BeerPresentation?>> = _beer

    private val _errorHandling = MutableStateFlow(Exception())
    val errorHandling: StateFlow<Exception> = _errorHandling
    var page: Int = 1
    private var textToSearch: String = ""

    fun geBeers() {
        if (textToSearch.isEmpty())
            launch {
                getBeersUseCase(page).let { result ->
                    when {
                        result.isSuccess ->
                            result.dataOrNull()?.let { beerPresentation ->
                                _beer.value = _beer.value + (beerPresentation.list ?: emptyList())
                            }
                        result.isError -> {
                            _errorHandling.value = result.getError()
                        }
                        else -> {}
                    }
                }
                page++
            }
    }

    fun geBeersByName(beerName: String) {
        _beer.value = emptyList()
        page = 0
        textToSearch = beerName
        if (textToSearch.isNotEmpty()) {
            page = 1
            launch {
                getBeersByNameUseCase(beerName).let { result ->
                    when {
                        result.isSuccess ->
                            result.dataOrNull()?.let { beerPresentation ->
                                _beer.value = beerPresentation.list ?: emptyList()
                            }

                        result.isError -> {
                            _errorHandling.value = result.getError()
                        }
                    }
                }

            }
        }
    }

}