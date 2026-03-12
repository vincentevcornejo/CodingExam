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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
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
                            style = MaterialTheme.typography.titleLarge
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

    val prices = remember { randomItems.map { getRandomPrice(1.0, 200.0) } }

    val totalPrice = round(prices.sum() * 100) / 100
    Column {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ){
            itemsIndexed(randomItems) {index, item ->
                val price = prices[index]
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = item,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "Php $price",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            item{
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    HorizontalDivider()
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Total",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "Php $totalPrice",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

            }
        }
    }
}

fun getRandomPrice(min: Double = 1.0, max: Double = 200.0): Double {
    val randomValue = Random.nextDouble(min, max)
    return round(randomValue * 100) / 100
}
