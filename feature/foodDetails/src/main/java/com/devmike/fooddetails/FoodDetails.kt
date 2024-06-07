package com.devmike.fooddetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dining
import androidx.compose.material.icons.filled.Earbuds
import androidx.compose.material.icons.filled.EggAlt
import androidx.compose.material.icons.filled.EnergySavingsLeaf
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.LineAxis
import androidx.compose.material.icons.filled.LocalParking
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.devmike.commonui.sharedui.CalorieItemScreen
import com.devmike.domain.models.CalorieModel

@Composable
fun FoodDetails(calorieModel: CalorieModel) {
    Column(
        modifier = Modifier.padding(16.dp).fillMaxSize().verticalScroll(rememberScrollState()),
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            CalorieItemScreen(calorieModel.calories)
        }

        NutritionDetailItem(
            modifier = Modifier,
            icon = Icons.Default.Dining,
            value = "${calorieModel.carbohydratesTotalG}g",
            label = "Carbs",
        )
        NutritionDetailItem(
            modifier = Modifier,
            icon = Icons.Default.Favorite,
            value = "${calorieModel.cholesterolMg}mg",
            label = "Cholesterol",
        )

        NutritionDetailItem(
            modifier = Modifier,
            icon = Icons.Default.FitnessCenter,
            value = "${calorieModel.fatTotalG}g",
            label = "Fat",
        )
        NutritionDetailItem(
            modifier = Modifier,
            icon = Icons.Default.EggAlt,
            value = "${calorieModel.proteinG}g",
            label = "Protein",
        )
        NutritionDetailItem(
            modifier = Modifier,
            icon = Icons.Default.Earbuds,
            value = "${calorieModel.sodiumMg}mg",
            label = "Sodium",
        )
        NutritionDetailItem(
            modifier = Modifier,
            icon = Icons.Default.EnergySavingsLeaf,
            value = "${calorieModel.sugarG}g",
            label = "Sugar",
        )
        NutritionDetailItem(
            modifier = Modifier,
            icon = Icons.Default.LocalParking,
            value = "${calorieModel.potassiumMg}mg",
            label = "Potassium",
        )
        NutritionDetailItem(
            modifier = Modifier,
            icon = Icons.Default.LineAxis,
            value = "${calorieModel.fiberG}g",
            label = "Fiber",
        )
    }
}

@Composable
fun NutritionDetailItem(
    modifier: Modifier,
    icon: ImageVector,
    value: String,
    label: String,
) {
    Card(
        modifier =
            Modifier
                .padding(8.dp)
                .fillMaxWidth(),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp), // Increased padding
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.size(70.dp),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = value,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }
}
