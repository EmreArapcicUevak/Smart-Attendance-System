package com.example.smartattendancesystemandroid.ui.screens.markattendancescreen

import android.content.Context
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import android.widget.Toast
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.smartattendancesystemandroid.scanner.BarcodeImageScanner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraScreen(
    onFinishTakingAttendance: () -> Unit,
    addAttendanceToTheList: (Long) -> Unit
) {

    val cameraPermissionState: PermissionState =
        rememberPermissionState(android.Manifest.permission.CAMERA)

    if (cameraPermissionState.status.isGranted) {
        CameraScreenStart(
            onFinishTakingAttendance = onFinishTakingAttendance,
            addAttendanceToTheList = addAttendanceToTheList
        )
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            LaunchedEffect(Unit) {
                cameraPermissionState.launchPermissionRequest()
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CameraScreenStart(
    onFinishTakingAttendance: () -> Unit,
    addAttendanceToTheList: (Long) -> Unit
) {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraController = remember {
        LifecycleCameraController(context)
    }

    val systemUiController: SystemUiController = rememberSystemUiController()
    systemUiController.isStatusBarVisible = false

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FilledTonalButton(
                onClick = onFinishTakingAttendance,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.width(100.dp)
            ) {
                Text(
                    text = "Finish",
                    fontWeight = FontWeight.SemiBold
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { context ->
                    PreviewView(context).apply {
                        layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                        setBackgroundColor(android.graphics.Color.TRANSPARENT)
                        scaleType = PreviewView.ScaleType.FILL_START
                    }.also { previewView ->
                        startBarcodeRecognition(
                            context = context,
                            cameraController = cameraController,
                            lifecycleOwner = lifecycleOwner,
                            previewView = previewView,
                            onBarcodeDetected = { value ->
                                checkBarcode(addAttendanceToTheList, value, context)
                            }
                        )
                    }
                }
            )
        }
    }
}

private fun startBarcodeRecognition(
    context: Context,
    cameraController: LifecycleCameraController,
    lifecycleOwner: LifecycleOwner,
    previewView: PreviewView,
    onBarcodeDetected: (String) -> Unit,
) {

    cameraController.setImageAnalysisAnalyzer(
        ContextCompat.getMainExecutor(context),
        BarcodeImageScanner(onBarcodeDetected = onBarcodeDetected)
    )

    previewView.controller = cameraController
    cameraController.bindToLifecycle(lifecycleOwner)
}

private fun checkBarcode(
    addAttendanceToTheList: (Long) -> Unit,
    value: String,
    context: Context
) {

    val id: Long? = value.toLongOrNull()

    if (id == null || id < 0) {
        Toast.makeText(context, "Invalid student id", Toast.LENGTH_SHORT).show()
        return
    }

    Toast.makeText(context, "Attendance Taken", Toast.LENGTH_SHORT).show()
    addAttendanceToTheList(id)
}