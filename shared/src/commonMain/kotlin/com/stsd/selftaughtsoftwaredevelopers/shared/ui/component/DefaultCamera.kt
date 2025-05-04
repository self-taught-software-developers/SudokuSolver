package com.stsd.selftaughtsoftwaredevelopers.shared.ui.component

//import android.util.Size
//import androidx.camera.core.CameraSelector
//import androidx.camera.core.ImageAnalysis
//import androidx.camera.core.ImageCapture
//import androidx.camera.core.Preview
//import androidx.camera.lifecycle.ProcessCameraProvider
//import androidx.camera.view.PreviewView
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.BoxWithConstraintsScope
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material.icons.rounded.Camera
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.platform.LocalDensity
//import androidx.compose.ui.platform.LocalLifecycleOwner
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.viewinterop.AndroidView
//import androidx.core.content.ContextCompat
//import com.google.accompanist.permissions.ExperimentalPermissionsApi
//import com.google.accompanist.permissions.isGranted
//import com.google.accompanist.permissions.rememberPermissionState
//import com.google.accompanist.permissions.shouldShowRationale
//import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.R.string.SUBTITLE_camera_permission_denied
//import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.R.string.SUBTITLE_camera_show_rationale
//import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.framework.manager.SudokuBoardAnalyzer
//import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.component.icon.rounded
//import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.board.TileState
//import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.util.AllPreviews
//import kotlinx.collections.immutable.PersistentList
//import kotlin.coroutines.resume
//import kotlin.coroutines.suspendCoroutine
//
//@Composable
//fun BoxWithConstraintsScope.Camera(
//    tiles: PersistentList<TileState>,
//    modifier: Modifier = Modifier,
//    onProcessed: (TileState) -> Unit = { }
//) {
//    calculateLocalPx(LocalDensity.current).let {
//        val context = LocalContext.current
//        val lifecycleOwner = LocalLifecycleOwner.current
//
//        val cameraSelector by remember { mutableStateOf(CameraSelector.DEFAULT_BACK_CAMERA) }
//        val previewView = remember { PreviewView(context) }
//
//        LaunchedEffect(cameraSelector) {
//            val executor = ContextCompat.getMainExecutor(context)
//
//            val cameraProvider = suspendCoroutine<ProcessCameraProvider> { con ->
//                ProcessCameraProvider.getInstance(context).also { future ->
//                    future.addListener({
//                        con.resume(future.get())
//                    }, executor)
//                }
//            }
//
//            val imageCapture = ImageCapture.Builder()
//                .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
//                .build()
//
//            val cameraPreview = Preview.Builder()
//                .build()
//                .also { it.setSurfaceProvider(previewView.surfaceProvider) }
//
//            val (width, height) = it.toIntPair()
//
//            val imageAnalyzer = ImageAnalysis.Builder()
//                .setTargetResolution(Size(width, height))
//                .build().also {
//                    it.setAnalyzer(
//                        executor,
//                        SudokuBoardAnalyzer(
//                            tileList = tiles,
//                            onProcessed = onProcessed
//                        )
//                    )
//                }
//
//            runCatching {
//                cameraProvider.unbindAll()
//                cameraProvider.bindToLifecycle(
//                    lifecycleOwner,
//                    cameraSelector,
//                    cameraPreview,
//                    imageCapture,
//                    imageAnalyzer
//                )
//            }
//        }
//
//        AndroidView(
//            modifier = modifier.fillMaxSize(),
//            factory = { previewView }
//        )
//    }
//}
//
//@OptIn(ExperimentalPermissionsApi::class)
//@Composable
//fun CameraPermissionRequest(
//    content: @Composable () -> Unit
//) {
//    val cameraPermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)
//
//    if (cameraPermissionState.status.isGranted) {
//        content()
//    } else {
//        RequestRationale(
//            icon = rounded.Camera,
//            shouldShowRationale = cameraPermissionState.status.shouldShowRationale
//        ) { cameraPermissionState.launchPermissionRequest() }
//    }
//}
//
//@Composable
//fun RequestRationale(
//    icon: ImageVector,
//    shouldShowRationale: Boolean,
//    modifier: Modifier = Modifier,
//    onPermissionRequest: () -> Unit = { }
//) {
//    Box(modifier = modifier.fillMaxSize()) {
//        IconTitle(
//            modifier = Modifier.align(Alignment.Center),
//            icon = icon,
//            text = stringResource(
//                id = if (shouldShowRationale) {
//                    SUBTITLE_camera_show_rationale
//                } else {
//                    SUBTITLE_camera_permission_denied
//                }
//            )
//        ) { onPermissionRequest() }
//    }
//}
//
//@AllPreviews
//@Composable
//fun CameraPermissionRequestPreview() {
//    RequestRationale(
//        icon = rounded.Camera,
//        shouldShowRationale = true
//    ) {
//    }
//}
