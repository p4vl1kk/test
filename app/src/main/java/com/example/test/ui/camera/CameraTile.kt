package com.example.test.ui.camera

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.test.model.camera_list.Camera


@Composable
fun CameraTile(
    camera: Camera
) {
    val cameraId = camera.videoStreams.first().accessPoint

    val model: CameraModel = viewModel(key = cameraId)

    DisposableEffect(Unit) {
        model.init(camera)
        onDispose {

        }
    }
    val state = model.state.observeAsState(initial = CameraState()).value

    Card(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (state.bitmap != null)
                Image(
                    modifier = Modifier
                        .fillMaxWidth(),
                    bitmap = state.bitmap.asImageBitmap(),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(Color.Gray.copy(alpha = 0.5f))
                    .padding(8.dp),
                text = camera.displayName,
                color = Color.White,
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.Center
            )

/*            if (state.loading)
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                )*/
        }
    }
}

