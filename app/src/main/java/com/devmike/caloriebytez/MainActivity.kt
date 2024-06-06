package com.devmike.caloriebytez

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.devmike.caloriebytez.ui.navigation.AppNavigation
import com.devmike.caloriebytez.ui.navigation.CalorieBottomBar
import com.devmike.caloriebytez.ui.theme.CalorieBytezTheme
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalorieBytezTheme {
                CalorieApp()
            }
        }
    }
}

@Composable
fun CalorieApp() {
    val navHostController: NavHostController = rememberNavController()

    Scaffold(bottomBar = {
        CalorieBottomBar(navHostController)
    }) {
        AppNavigation(
            modifier =
                Modifier.padding(
                    bottom = it.calculateBottomPadding(),
                ),
            navController = navHostController,
        )
    }
}
