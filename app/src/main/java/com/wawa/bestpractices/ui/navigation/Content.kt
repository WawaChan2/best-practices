package com.wawa.bestpractices.ui.navigation

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.wawa.bestpractices.ui.MIN_GRID_WIDTH
import com.wawa.bestpractices.ui.screen.FeedScreen
import com.wawa.bestpractices.ui.screen.ListDetailScreen
import com.wawa.bestpractices.ui.screen.SupportingPaneScreen

fun EntryProviderScope<NavKey>.featureListDetailSection() {
  entry<Screen.ListDetail> {
    ListDetailScreen()
  }
}

fun EntryProviderScope<NavKey>.featureSupportingPaneSection() {
  entry<Screen.SupportingPane> {
    SupportingPaneScreen()
  }
}

fun EntryProviderScope<NavKey>.featureFeedSection() {
  entry<Screen.Feed> {
    FeedScreen(columns = GridCells.Adaptive(minSize = MIN_GRID_WIDTH.dp))
  }
}