package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component

import android.util.Size
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.framework.manager.SudokuBoardAnalyzer
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Composable
fun Camera(
    modifier: Modifier = Modifier,
    size: Pair<Float,Float>,
    cellList: List<Cell>,
    onProcessed: (TileState) -> Unit
) {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val cameraSelector by remember { mutableStateOf(CameraSelector.DEFAULT_BACK_CAMERA) }
    val previewView = remember { PreviewView(context) }

    LaunchedEffect(cameraSelector) {

        val executor = ContextCompat.getMainExecutor(context)

        val cameraProvider = suspendCoroutine<ProcessCameraProvider> { con ->
            ProcessCameraProvider.getInstance(context).also { future ->
                future.addListener({
                    con.resume(future.get())
                }, executor)
            }
        }

        val imageCapture = ImageCapture.Builder()
            .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
            .build()

        val cameraPreview = Preview.Builder()
            .build()
            .also { it.setSurfaceProvider(previewView.surfaceProvider) }

        val (width, height) = size.toInt()

        val imageAnalyzer = ImageAnalysis.Builder()
            .setTargetResolution(Size(width, height))
            .build().also {
                it.setAnalyzer(
                    executor,
                    SudokuBoardAnalyzer(
                        cellList = cellList,
                        onProcessed = onProcessed
                    )
                )
            }

        runCatching {
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                lifecycleOwner,
                cameraSelector,
                cameraPreview,
                imageCapture,
                imageAnalyzer
            )
        }

    }

    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { previewView }
    )

}