package com.pascal.templateprojectcompose.ui.component.signature

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.MotionEvent
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.drawToBitmap
import com.pascal.templateprojectcompose.ui.component.button.ButtonComponent

@Composable
fun ComposeSignature(
    modifier: Modifier = Modifier,
    signaturePadColor: Color = Color(0xFFEEEEEE),
    signaturePadHeight: Dp = 500.dp,
    signatureColor: Color = Color.Black,
    signatureThickness: Float = 10f,
    hasAlpha: Boolean = true,
    completeComponent: @Composable (onClick: () -> Unit) -> Unit = { it ->
        ButtonComponent(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(start = 4.dp),
            text = "Simpan",
            onClick = {
                it()
            },
        )
    },
    clearComponent: @Composable (onClick: () -> Unit) -> Unit = {
        ButtonComponent(
            modifier = Modifier
                .fillMaxWidth(.5f)
                .height(48.dp)
                .padding(end = 4.dp),
            text = "Reset",
            color = MaterialTheme.colorScheme.onError,
            onClick = {
                it()
            },
        )
    },
    onComplete: (Bitmap) -> Unit,
    onClear: () -> Unit = {},
) {
    @SuppressLint("MutableCollectionMutableState")
    var path by rememberSaveable {
        mutableStateOf(mutableListOf<PathState>())
    }
    var signatureBitmap = captureBitmap {}
    val drawColor = remember { mutableStateOf(signatureColor) }
    val drawBrush = remember { mutableFloatStateOf(signatureThickness) }
    val context = LocalContext.current

    Column {
        path.add(PathState(Path(), drawColor.value, drawBrush.value))

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(12.dp))
        ) {
            signatureBitmap = captureBitmap {
                DrawingCanvas(
                    drawColor = drawColor,
                    drawBrush = drawBrush,
                    path = path,
                    modifier = modifier.height(signaturePadHeight),
                    signaturePadColor = signaturePadColor,
                )
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            clearComponent {
                onClear()
                path = mutableListOf()
            }

            completeComponent {
                val isEmpty = path.last().path.isEmpty
                if (isEmpty) {
                    Toast.makeText(
                        context,
                        "Signature is empty",
                        Toast.LENGTH_SHORT,
                    ).show()
                    return@completeComponent
                } else {
                    onComplete(
                        signatureBitmap.invoke().apply {
                            setHasAlpha(hasAlpha)
                        },
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DrawingCanvas(
    drawColor: MutableState<Color>,
    drawBrush: MutableState<Float>,
    path: MutableList<PathState>,
    modifier: Modifier,
    signaturePadColor: Color,
) {
    val currentPath = path.last().path
    val movePath = remember { mutableStateOf<Offset?>(null) }

    Canvas(
        modifier = modifier
            .background(signaturePadColor)
            .pointerInteropFilter {
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> {
                        currentPath.moveTo(it.x, it.y)
                    }

                    MotionEvent.ACTION_MOVE -> {
                        movePath.value = Offset(it.x, it.y)
                    }

                    else -> {
                        movePath.value = null
                    }
                }
                true
            },
    ) {
        movePath.value?.let {
            currentPath.lineTo(it.x, it.y)
            drawPath(
                path = currentPath,
                color = drawColor.value,
                style = Stroke(drawBrush.value),
            )
        }
        path.forEach {
            drawPath(
                path = it.path,
                color = it.color,
                style = Stroke(it.stroke),
            )
        }
    }
}

@Composable
fun captureBitmap(
    content: @Composable () -> Unit,
): () -> Bitmap {
    val context = LocalContext.current

    /**
     * ComposeView that would take composable as its content
     * Kept in remember so recomposition doesn't re-initialize it
     **/
    val composeView = remember { ComposeView(context) }

    /**
     * Callback function which could get latest image bitmap
     **/
    fun captureBitmap(): Bitmap {
        return composeView.drawToBitmap()
    }

    /** Use Native View inside Composable **/
    AndroidView(
        factory = {
            composeView.apply {
                setContent {
                    content.invoke()
                }
            }
        },
    )

    /** returning callback to bitmap **/
    return ::captureBitmap
}