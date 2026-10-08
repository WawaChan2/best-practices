package com.wawa.bestpractices.ui.screen

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wawa.bestpractices.ui.COMPACT_PREVIEW_WIDTH
import com.wawa.bestpractices.ui.EXPANDED_PREVIEW_WIDTH
import com.wawa.bestpractices.ui.MEDIUM_PREVIEW_WIDTH
import com.wawa.bestpractices.ui.MIN_GRID_WIDTH
import com.wawa.bestpractices.ui.theme.BestPracticesTheme

@Composable
fun FeedScreen(
  columns: GridCells,
  modifier: Modifier = Modifier
) {
  LazyVerticalGrid(
    columns = columns,
    modifier = modifier
  ) {

  }
}

@Preview(widthDp = COMPACT_PREVIEW_WIDTH, showBackground = true)
@Composable
fun CompactFeedScreen() {
  BestPracticesTheme {
    FeedScreen(columns = GridCells.Adaptive(minSize = MIN_GRID_WIDTH.dp))
  }
}

@Preview(widthDp = MEDIUM_PREVIEW_WIDTH, showBackground = true)
@Composable
fun MediumFeedScreen() {
  BestPracticesTheme {
    FeedScreen(columns = GridCells.Adaptive(minSize = MIN_GRID_WIDTH.dp))
  }
}

@Preview(widthDp = EXPANDED_PREVIEW_WIDTH, showBackground = true)
@Composable
fun ExpandedFeedScreen() {
  BestPracticesTheme {
    FeedScreen(columns = GridCells.Adaptive(minSize = MIN_GRID_WIDTH.dp))
  }
}