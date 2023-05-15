package com.jhonny.punkbeer.presenter.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.jhonny.punkbeer.extensions.isScrolledToTheEnd
import com.jhonny.punkbeer.presenter.entities.BeerPresentation
import com.jhonny.punkbeer.presenter.navigation.AppScreens
import com.jhonny.punkbeer.ui.theme.PunkBeerTheme
import com.jhonny.punkbeer.util.setStyleBold


@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel) {
    val beerState by viewModel.beer.collectAsState()
    val errorHandling by viewModel.errorHandling.collectAsState()
    PunkBeerTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                SearchBar(viewModel)
                if (errorHandling.message != null) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            model = "https://cdn0.iconfinder.com/data/icons/tools-41/432/not-found-512.png",
                            contentDescription = "empty image",
                            modifier = Modifier
                                .size(128.dp)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(24.dp))
                        )
                    }
                } else {
                    BeerList(beerState, viewModel, navController)
                }
            }
        }
    }
}

@Composable
fun SearchBar(viewModel: MainViewModel?) {
    val (value, onValueChange) = remember { mutableStateOf("") }
    if(value.isNotEmpty())
        viewModel?.geBeersByName(value)
    TextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = TextStyle(fontSize = 17.sp),
        leadingIcon = { Icon(Icons.Filled.Search, null, tint = Color.Gray) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp, top = 20.dp, bottom = 10.dp)
            .background(Color(0xFFE7F1F1), RoundedCornerShape(16.dp)),
        placeholder = { Text(text = "Punk beers") },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = Color.Transparent,
            cursorColor = Color.DarkGray
        )
    )
}

@Composable
fun BeerList(beer: List<BeerPresentation?>, viewModel: MainViewModel?, navController: NavController?) {
    val state = rememberLazyListState()
    LazyColumn(state = state, modifier = Modifier.padding(start = 16.dp, end = 8.dp) ) {
        items(beer) { beerItem ->
            BeerItem(beerItem, navController)
        }
    }
    if(state.isScrolledToTheEnd()){
        viewModel?.geBeers()
    }
}

@Composable
fun BeerItem(beer: BeerPresentation?, navController: NavController?) {
    Row(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .clickable {
                navController?.currentBackStackEntry?.savedStateHandle?.set(
                    key = "beer",
                    value = beer
                )
                navController?.navigate(AppScreens.DetailScreen.route)
            }
    ) {
        AsyncImage(
            model = beer?.imageUrl.orEmpty(),
            contentDescription = "beer image",
            modifier = Modifier
                .size(128.dp)
                .clip(RoundedCornerShape(24.dp))
        )
        Column(modifier = Modifier
            .padding(start = 8.dp)
            .heightIn(min = 128.dp), verticalArrangement = Arrangement.Center) {
            Spacer(modifier = Modifier.weight(1.0F))
            Text(setStyleBold("Nombre: ", beer?.name.orEmpty()))
            Spacer(modifier = Modifier.weight(1.0F))
            Text(setStyleBold("Abv: ", beer?.abv.toString()))
            Spacer(modifier = Modifier.weight(1.0F))
            Text(setStyleBold("Ph: ", beer?.ph.toString()))
            Spacer(modifier = Modifier.weight(1.0F))
        }
    }
}

@Preview(showBackground = true)
@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
    PunkBeerTheme {
        Column(modifier = Modifier.fillMaxWidth()) {
            SearchBar(null)
            Text(text = "Punk beer", textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp))
            BeerList(
                listOf(BeerPresentation()),
                null
                ,null)
        }
    }
}
