package com.devmike.fooddetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodDetailsScreen(
    foodDetailViewModel: FoodDetailViewModel = hiltViewModel(),
    onBackTapped: () -> Unit,
) {
    val details by foodDetailViewModel.foodDetails.collectAsStateWithLifecycle()

    val topBarTitle =
        remember(details) {
            derivedStateOf {
                details?.name ?: "Details"
            }
        }

    Scaffold(topBar = {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = onBackTapped) {
                    Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Back")
                }
            },
            title = {
                Text(topBarTitle.value, fontWeight = FontWeight.Bold)
            },
        )
    }) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            details?.let {
                FoodDetails(it)
            } ?: kotlin.run {
                Column {
                    Text(" We could not find details for the current food Item \n")

                    FilledTonalButton(onBackTapped) {
                        Text("Go Back")
                    }
                }
            }
        }
    }
}
