package com.wawa.bestpractices.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.wawa.bestpractices.R

val TOP_LEVEL_DESTINATIONS = mapOf(
  Screen.ListDetail to NavBarItem(
    iconId = R.drawable.list_24px,
    labelId = R.string.list_detail_label
  ),
  Screen.SupportingPane to NavBarItem(
    iconId = R.drawable.view_sidebar_24px,
    labelId = R.string.supporting_pane_label
  ),
  Screen.Feed to NavBarItem(
    iconId = R.drawable.grid_view_24px,
    labelId = R.string.feed_label
  )
)

data class NavBarItem(
  @DrawableRes val iconId: Int,
  @StringRes val labelId: Int
)