package com.wawa.bestpractices.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfoV2
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.wawa.bestpractices.R
import com.wawa.bestpractices.ui.navigation.Navigator
import com.wawa.bestpractices.ui.navigation.Screen
import com.wawa.bestpractices.ui.navigation.TOP_LEVEL_DESTINATIONS
import com.wawa.bestpractices.ui.navigation.featureFeedSection
import com.wawa.bestpractices.ui.navigation.featureListDetailSection
import com.wawa.bestpractices.ui.navigation.featureSupportingPaneSection
import com.wawa.bestpractices.ui.navigation.rememberNavigationState

@Composable
fun BestPracticesApp() {
  val adaptiveInfo = currentWindowAdaptiveInfoV2()
  val customNavType = NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(adaptiveInfo)

  val navigationState = rememberNavigationState(
    startDestination = Screen.ListDetail,
    topLevelDestinations = TOP_LEVEL_DESTINATIONS.keys
  )
  val navigator = remember { Navigator(state = navigationState) }
  val entryProvider = entryProvider {
    featureListDetailSection()
    featureSupportingPaneSection()
    featureFeedSection()
  }

  NavigationSuiteScaffold(
    navigationSuiteItems = {
      TOP_LEVEL_DESTINATIONS.forEach { (topLevelDestination, data) ->
        val isSelected = topLevelDestination == navigationState.topLevelDestination

        item(
          selected = isSelected,
          onClick = {
            navigator.navigate(topLevelDestination)

            if (isSelected) navigator.onReselect(topLevelDestination)
          },
          icon = {
            Icon(
              imageVector = ImageVector.vectorResource(data.iconId),
              contentDescription = stringResource(data.labelId)
            )
          },
          label = {
            Text(text = stringResource(data.labelId))
          }
        )
      }
    },
    layoutType = customNavType
  ) {
    Scaffold(
      modifier = Modifier.fillMaxSize(),
      topBar = {
        BestPracticesTopAppBar()
      }
    ) { innerPadding ->
      NavDisplay(
        entries = navigationState.toDecoratedEntries(entryProvider),
        onBack = { navigator.goBack() },
        modifier = Modifier.padding(innerPadding)
      )
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BestPracticesTopAppBar(modifier: Modifier = Modifier) {
  TopAppBar(
    title = {
      Text(text = stringResource(R.string.app_name))
    },
    modifier = modifier
  )
}