package com.example.amphibiansapp.uilayer

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibiansapp.R
import com.example.amphibiansapp.datalayer.AmphibianPhoto
import com.example.amphibiansapp.ui.theme.AmphibiansAppTheme


@Composable
fun AmphibiansApp(amphibianViewModel: AmphibianViewModel, modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { TopAppBar(title = { Text(stringResource(R.string.app_name)) }) }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colors.background
        ) {
            val amphibianViewModel: AmphibianViewModel = viewModel()
            HomeScreen(amphibianState = amphibianViewModel.amphibianState)
        }
    }
}


@Composable
fun AmphibianScreen(amphibians: List<AmphibianPhoto>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        items(amphibians) { myAmphibian ->
            AmphibianCard(myAmphibian = myAmphibian)
        }
    }
}


@Composable
fun AmphibianCard(myAmphibian: AmphibianPhoto) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .aspectRatio(1f),
        elevation = 8.dp,
    ) {
        Column (
            modifier = Modifier.padding(3.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
                ) {
            Text(modifier = Modifier.padding(start = 7.dp, end = 7.dp), text = "${myAmphibian.name} (${myAmphibian.type})", fontWeight = FontWeight.Bold)
            Text(modifier = Modifier.padding(3.dp), text = myAmphibian.description, textAlign = TextAlign.Justify)
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(myAmphibian.imgSrc).crossfade(true).build(),
                error = painterResource(id = R.drawable.ic_broken_image),
                placeholder = painterResource(id = R.drawable.loading_img),
                contentScale = ContentScale.Crop,
                contentDescription = "Amphibian Image"
            )
        }
    }
}

@Composable
fun HomeScreen(amphibianState: AmphibianState) {
    when(amphibianState) {
        is AmphibianState.Success -> AmphibianScreen(amphibianState.photos)
        is AmphibianState.Loading -> LoadingScreen()
        is AmphibianState.Error -> ErrorScreen()
    }
}


@Composable
fun LoadingScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.loading_img), contentDescription = "Loading", modifier = Modifier.size(200.dp))
    }
}


@Composable
fun ErrorScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.error), contentDescription = "Loading", modifier = Modifier.size(200.dp))
    }
}


@Preview(showBackground = true)
@Composable
fun AmphibianCardPreview() {
    AmphibiansAppTheme() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AmphibianCard(myAmphibian = AmphibianPhoto(name = "FrogName", type = "FRog", "DEscription", imgSrc = "${R.drawable.img}"))
        }
    }
}