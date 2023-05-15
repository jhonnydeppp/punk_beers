package com.jhonny.punkbeer.presenter.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.jhonny.punkbeer.presenter.entities.BeerPresentation
import com.jhonny.punkbeer.util.setStyleBold


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController, beerDetail: BeerPresentation) {
    Scaffold {
        Body(beerDetail, navController)
    }
}

@Composable
fun Body(beer: BeerPresentation, navController: NavController) {
    BeerDetail(beer, navController)
}

@Composable
fun BeerDetail(beer: BeerPresentation, navController: NavController) {
        Column(modifier = Modifier
            .heightIn(min = 128.dp)
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = CenterHorizontally) {
            Spacer(modifier = Modifier.weight(1.0F))
            AsyncImage(
                model = beer.imageUrl.orEmpty(),
                contentDescription = "beer image",
                modifier = Modifier
                    .size(128.dp)
                    .clip(RoundedCornerShape(24.dp))
            )
            Spacer(modifier = Modifier.weight(1.0F))
            Text(setStyleBold("Nombre: ", beer.name.orEmpty()))
            Spacer(modifier = Modifier.weight(1.0F))
            Text(setStyleBold("Abv: ", beer.abv.toString()))
            Spacer(modifier = Modifier.weight(1.0F))
            Text(setStyleBold("Ph: ", beer.ph.toString()))
            Spacer(modifier = Modifier.weight(1.0F))
            Text(setStyleBold("Description: ", beer.description.orEmpty()))
            Spacer(modifier = Modifier.weight(10.0F))
            Button(onClick = {
                navController.popBackStack()
            }) {
                Text("Regresar")
            }
            Spacer(modifier = Modifier.weight(1.0F))
        }
}