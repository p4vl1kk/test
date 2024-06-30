package com.example.test

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainScreen(
    navigateToSnapshots: () -> Unit,
    navigateToCameras: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { navigateToSnapshots() },
                modifier = Modifier
                    .padding(16.dp)
                    .width(200.dp)
                    .height(48.dp)
            ) {
                Text("Snapshots", fontSize = 18.sp)
            }

            Button(
                onClick = { navigateToCameras() },
                modifier = Modifier
                    .padding(16.dp)
                    .width(200.dp)
                    .height(48.dp)
            ) {
                Text("Cameras", fontSize = 18.sp)
            }
        }
    }
}
