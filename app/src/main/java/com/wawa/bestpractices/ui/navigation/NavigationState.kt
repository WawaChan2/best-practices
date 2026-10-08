package com.wawa.bestpractices.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.compose.runtime.setValue
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberDecoratedNavEntries
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.runtime.serialization.NavKeySerializer
import androidx.savedstate.compose.serialization.serializers.MutableStateSerializer

/**
 * Create a navigation state that persists config changes and process death.
 *
 * @param startDestination - The top level destination to start on. This should also be in `topLevelDestinations`.
 * @param topLevelDestinations - The top level destinations in the app.
 */
@Composable
fun rememberNavigationState(
  startDestination: NavKey,
  topLevelDestinations: Set<NavKey>
): NavigationState {
  val topLevelDestination = rememberSerializable(
    startDestination,
    topLevelDestinations,
    serializer = MutableStateSerializer(NavKeySerializer())
  ) {
    mutableStateOf(startDestination)
  }

  // Create a back stack for each top level route.
  val backStacks = topLevelDestinations.associateWith { key -> rememberNavBackStack(key) }

  return remember(startDestination, topLevelDestinations) {
    NavigationState(
      startDestination = startDestination,
      topLevelDestination = topLevelDestination,
      backStacks = backStacks
    )
  }
}

/**
 * State holder for navigation state. This class does not modify its own state. It is designed
 * to be modified using the `Navigator` class.
 *
 * @param startDestination - the start destination. The user will exit the app through this destination.
 * @param topLevelDestination - the state object that backs the top level destination.
 * @param backStacks - the back stacks for each top level destination.
 */
class NavigationState(
  val startDestination: NavKey,
  topLevelDestination: MutableState<NavKey>,
  val backStacks: Map<NavKey, NavBackStack<NavKey>>
) {
  /**
   * The top level destination.
   */
  var topLevelDestination by topLevelDestination

  /**
   * Convert the navigation state into `NavEntry`s that have been decorated with a
   * `SaveableStateHolder`.
   *
   * @param entryProvider - the entry provider used to convert the keys in the
   * back stacks to `NavEntry`s.
   */
  @Composable
  fun toDecoratedEntries(
    entryProvider: (NavKey) -> NavEntry<NavKey>
  ): List<NavEntry<NavKey>> {
    // For each back stack, create a `SaveableStateHolder` decorator and use it to decorate
    // the entries from that stack. When backStacks changes, `rememberDecoratedNavEntries` will
    // be recomposed and a new list of decorated entries is returned.
    val decoratedEntries = backStacks.mapValues { (_, stack) ->
      val decorators = listOf(
        rememberSaveableStateHolderNavEntryDecorator<NavKey>()
      )
      rememberDecoratedNavEntries(
        backStack = stack,
        entryDecorators = decorators,
        entryProvider = entryProvider
      )
    }

    // Only return the entries for the stacks that are currently in use.
    return getTopLevelDestinationsInUse()
      .flatMap { decoratedEntries[it] ?: emptyList() }
  }

  /**
   * Get the top level destinations that are currently in use. The start destination is always the first destination
   * in the list. This means the user will always exit the app through the starting destination
   * ("exit through home" pattern). The list will contain a maximum of one other destination. This is a
   * design decision. In your app, you may wish to allow more than two top level destinations to be
   * active.
   *
   * Note that even if a top level destination is not in use its state is still retained.
   *
   * @return the current top level destinations that are in use.
   */
  private fun getTopLevelDestinationsInUse(): List<NavKey> =
    if (topLevelDestination == startDestination) {
      listOf(startDestination)
    } else {
      listOf(startDestination, topLevelDestination)
    }
}