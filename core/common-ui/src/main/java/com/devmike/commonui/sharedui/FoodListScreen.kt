package com.devmike.commonui.sharedui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.GeneratingTokens
import androidx.compose.material.icons.filled.OilBarrel
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.devmike.domain.models.CalorieModel

@Composable
fun FoodList(
    ingredients: List<CalorieModel>,
    onFoodItemClicked: (name: String) -> Unit,
) {
    LazyColumn(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        items(ingredients, key = { it.name }) { ingredient ->
            FoodItemScreen(ingredient) {
                onFoodItemClicked(ingredient.name)
            }
        }
    }
}

@Composable
fun FoodItemScreen(
    calorieModel: CalorieModel,
    onItemClicked: () -> Unit,
) {
    OutlinedCard(
        onClick = onItemClicked,
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = calorieModel.name,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                CalorieItemScreen(calorieModel.calories)
            }
            Text(text = "Per a ${calorieModel.servingSizeinGrams}g")

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                NutritionItem(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Default.GeneratingTokens,
                    value = "${calorieModel.carbohydratesTotalG}g",
                    label = "Carbs",
                )
                NutritionItem(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Default.OilBarrel,
                    value = "${calorieModel.cholesterolMg}mg",
                    label = "Cholesterol",
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                NutritionItem(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Default.FitnessCenter,
                    value = "${calorieModel.fatTotalG}g",
                    label = "Fat",
                )
                NutritionItem(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Default.Fastfood,
                    value = "${calorieModel.proteinG}g",
                    label = "Protein",
                )
            }
        }
    }
}

@Composable
fun NutritionItem(
    modifier: Modifier,
    icon: ImageVector,
    value: String,
    label: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.size(16.dp),
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}
