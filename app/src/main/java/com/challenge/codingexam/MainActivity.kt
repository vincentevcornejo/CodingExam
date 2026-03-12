package com.challenge.codingexam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.math.round
import kotlin.random.Random

class MainActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val randomItems = listOf(
            "Toothpaste",
            "Soap",
            "Shampoo",
            "Hand sanitizer",
            "Deodorant",
            "Body lotion",
            "Moisturizer",
        )

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(scrim = 0, darkScrim = 0),
            navigationBarStyle = SystemBarStyle.light(scrim = 0, darkScrim = 0),
        )
        setContent {
            MaterialTheme {
                Scaffold(
                    contentWindowInsets = WindowInsets(0, 0, 0, 0),
                ) { innerPadding ->
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .statusBarsPadding()
                        .navigationBarsPadding(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(
                            text = "Sales",
                            style = MaterialTheme.typography.titleSmall
                        )
                        RandomItemPriceList(
                            modifier = Modifier.padding(16.dp),
                            randomItems = randomItems)
                    }
                }
            }
        }
    }
}

@Composable
private fun RandomItemPriceList(
    modifier: Modifier = Modifier,
    randomItems: List<String>
){
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ){
        items(randomItems){
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = it)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Php ${getRandomPrice()}")
            }
        }
    }
}

fun getRandomPrice(min: Double = 1.0, max: Double = 200.0): Double {
    val randomValue = Random.nextDouble(min, max)
    return round(randomValue * 100) / 100
}
