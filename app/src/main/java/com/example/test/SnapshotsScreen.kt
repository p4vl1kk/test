package com.example.test

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.graphics.Bitmap // Ensure this import is correct
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun SnapshotsScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Snapshots Screen",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // Remember to use remember for state management
        val bitmapState = remember { mutableStateOf<Bitmap?>(null) }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
        ) {
            //fixme хардкод
            val snapshotIndices = (1..6).toList()
            items(snapshotIndices) { index ->
                SnapshotTile(index = index, bitmapState = bitmapState)
            }
        }
    }
}

@Composable
fun SnapshotTile(index: Int, bitmapState: MutableState<Bitmap?>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        contentAlignment = Alignment.Center
    ) {
        bitmapState.value?.let { bitmap ->
            Image(
                painter = remember(bitmap) { BitmapPainter(bitmap.asImageBitmap()) },
                contentDescription = null
            )
        } ?: run {
            Text(text = "No Bitmap available")
        }
    }
}