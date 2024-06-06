package com.devmike.search

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.devmike.domain.models.CalorieModel
import com.devmike.domain.models.FetchItemState

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel = hiltViewModel(),
    onFoodItemClicked: (food: String) -> Unit,
) {
    val recentSearches by searchViewModel.recentSearchList.collectAsStateWithLifecycle()

    var searchActive by remember {
        mutableStateOf(false)
    }

    val idleComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottieupjson))
    val loadingComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.foodloading))

    val calorieSearchState by searchViewModel.caloriesSearchState.collectAsStateWithLifecycle()
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CalorieSearchBar(
                query = searchViewModel.searchQuery.value,
                onQueryChange = searchViewModel::modifyQuery,
                onSearch = {
                    searchViewModel.searchCalories()
                    searchActive = false
                },
                isActive = searchActive,
                onActiveChanged = {
                    searchActive = it
                },
            ) {
                RecentSearchesBody(recentSearches.toList(), onClearRecentSearches = {
                }) {
                    searchViewModel.modifyQuery(it)
                    searchViewModel.searchCalories()
                    searchActive = false
                }
            }

            when (val state = calorieSearchState) {
                is FetchItemState.Error -> {
                    Text(state.err.message)
                }

                is FetchItemState.Idle -> {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(top = 50.dp),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        LottieAnimation(idleComposition, modifier = Modifier.size(200.dp), iterations = 20)
                        Text("Tap to search for nutrition data", fontWeight = FontWeight.Bold)
                    }
                }

                FetchItemState.Loading -> {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(top = 50.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        LottieAnimation(loadingComposition, modifier = Modifier.size(200.dp), iterations = 20)
                        Text("we are getting your data", fontWeight = FontWeight.Bold)
                    }
                }

                is FetchItemState.Success -> {
                    com.devmike.commonui.sharedui.FoodList(state.data, onFoodItemClicked)
                }
            }
        }
    }
}

@Composable
fun CalorieListItem(
    calorieModel: CalorieModel,
    onClicked: () -> Unit,
) {
    Card(onClick = onClicked) {
        Text(calorieModel.name)
    }
}
