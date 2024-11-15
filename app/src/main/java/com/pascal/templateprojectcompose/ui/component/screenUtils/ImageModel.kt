package com.pascal.templateprojectcompose.ui.component.screenUtils

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import coil.request.ImageRequest
import com.pascal.templateprojectcompose.R

@Composable
fun ImageModel(
    context: Context,
    url: String? = ""
): ImageRequest {
    val model = remember {
        ImageRequest.Builder(context)
            .data(url)
            .size(1024)
            .crossfade(true)
            .error(R.drawable.no_thumbnail)
            .build()
    }
    return model

}
