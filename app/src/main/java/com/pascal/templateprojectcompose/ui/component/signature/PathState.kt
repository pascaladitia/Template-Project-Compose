package com.pascal.templateprojectcompose.ui.component.signature

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path

data class PathState(
    val path: Path,
    val color: Color,
    val stroke: Float,
)