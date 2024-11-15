package com.pascal.templateprojectcompose.ui.screen.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pascal.templateprojectcompose.ui.theme.SampleAppTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    viewModel: HomeViewModel = koinViewModel(),
    onDetail: () -> Unit
) {

}


@Preview(showBackground = true)
@Composable
private fun HomePreview() {
    SampleAppTheme {  }
}