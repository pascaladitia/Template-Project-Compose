package com.pascal.templateprojectcompose.ui.screen.live

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pascal.templateprojectcompose.ui.theme.SampleAppTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun LiveScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    viewModel: LiveViewModel = koinViewModel(),
    onDetail: () -> Unit
) {

}


@Preview(showBackground = true)
@Composable
private fun LivePreview() {
    SampleAppTheme {  }
}