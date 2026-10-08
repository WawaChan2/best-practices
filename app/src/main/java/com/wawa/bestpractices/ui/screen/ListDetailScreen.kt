package com.wawa.bestpractices.ui.screen

import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wawa.bestpractices.ui.COMPACT_PREVIEW_WIDTH
import com.wawa.bestpractices.ui.EXPANDED_PREVIEW_WIDTH
import com.wawa.bestpractices.ui.MEDIUM_PREVIEW_WIDTH
import com.wawa.bestpractices.ui.theme.BestPracticesTheme

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ListDetailScreen(modifier: Modifier = Modifier) {
  val navigator = rememberListDetailPaneScaffoldNavigator()

  NavigableListDetailPaneScaffold(
    navigator = navigator,
    listPane = {
      Text(text = "list")
    },
    detailPane = {
      Text(text = "detail")
    },
    modifier = modifier
  )
}

@Preview(widthDp = COMPACT_PREVIEW_WIDTH, showBackground = true)
@Composable
fun CompactListDetailScreenPreview() {
  BestPracticesTheme {
    ListDetailScreen()
  }
}

@Preview(widthDp = MEDIUM_PREVIEW_WIDTH, showBackground = true)
@Composable
fun MediumListDetailScreenPreview() {
  BestPracticesTheme {
    ListDetailScreen()
  }
}

@Preview(widthDp = EXPANDED_PREVIEW_WIDTH, showBackground = true)
@Composable
fun ExpandedListDetailScreenPreview() {
  BestPracticesTheme {
    ListDetailScreen()
  }
}