package com.hidenobi.recognitiondog.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hidenobi.base.ui.base.compose.ComposeActivity
import com.hidenobi.recognitiondog.ui.theme.RecognitionDogTheme

class MainActivity : ComposeActivity() {
    @Composable
    override fun SetContentView() {
        RecognitionDogTheme {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                Greeting(
                    name = "Android",
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RecognitionDogTheme {
        Greeting("Android")
    }
}