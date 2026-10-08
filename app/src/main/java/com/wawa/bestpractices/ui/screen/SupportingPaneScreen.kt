package com.wawa.bestpractices.ui.screen

import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation.NavigableSupportingPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberSupportingPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wawa.bestpractices.ui.COMPACT_PREVIEW_WIDTH
import com.wawa.bestpractices.ui.EXPANDED_PREVIEW_WIDTH
import com.wawa.bestpractices.ui.MEDIUM_PREVIEW_WIDTH
import com.wawa.bestpractices.ui.theme.BestPracticesTheme

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun SupportingPaneScreen(modifier: Modifier = Modifier) {
  val navigator = rememberSupportingPaneScaffoldNavigator()

  NavigableSupportingPaneScaffold(
    navigator = navigator,
    mainPane = {
      Text(text = "main")
    },
    supportingPane = {
      Text(text = "supporting")
    },
    modifier = modifier
  )
}

@Preview(widthDp = COMPACT_PREVIEW_WIDTH, showBackground = true)
@Composable
fun CompactSupportingPaneScreenPreview() {
  BestPracticesTheme {
    SupportingPaneScreen()
  }
}

@Preview(widthDp = MEDIUM_PREVIEW_WIDTH, showBackground = true)
@Composable
fun MediumSupportingPaneScreenPreview() {
  BestPracticesTheme {
    SupportingPaneScreen()
  }
}

@Preview(widthDp = EXPANDED_PREVIEW_WIDTH, showBackground = true)
@Composable
fun ExpandedSupportingPaneScreenPreview() {
  BestPracticesTheme {
    SupportingPaneScreen()
  }
}