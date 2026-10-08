package com.wawa.bestpractices.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen : NavKey {

  @Serializable
  data object ListDetail : Screen

  @Serializable
  data object SupportingPane : Screen

  @Serializable
  data object Feed : Screen
}