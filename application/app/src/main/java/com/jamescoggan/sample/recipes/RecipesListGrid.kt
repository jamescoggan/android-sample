package com.jamescoggan.sample.recipes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jamescoggan.sample.testingData.recommendedRecipesSamples
import com.jamescoggan.sample.ui.isPortraitMode
import com.jamescoggan.sample.ui.models.RecipeUiItem
import com.jamescoggan.sample.ui.theme.AndroidAppSampleTheme
import kotlinx.collections.immutable.ImmutableList

@Composable
fun RecipeListGrid(
    recipesList: ImmutableList<RecipeUiItem>,
    isRefreshing: Boolean,
    modifier: Modifier = Modifier,
    onItemClick: (RecipeUiItem) -> Unit,
    onRefresh: () -> Unit
) {
    val pullRefreshState =
        rememberPullRefreshState(refreshing = isRefreshing, onRefresh = onRefresh)

    val focusRequester = remember { FocusRequester() }
    Box(
        modifier = modifier
            .pullRefresh(pullRefreshState)
            .focusRequester(focusRequester)
    ) {
        val columnSpan = if (isPortraitMode()) 2 else 3

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(
                columnSpan
            ),
            modifier = Modifier.fillMaxSize(),
            verticalItemSpacing = 16.dp,
            horizontalArrangement = Arrangement.spacedBy(
                16.dp
            ),
            contentPadding = PaddingValues(
                16.dp
            ),
        ) {
            items(count = recipesList.size, key = { i -> recipesList[i].id }) { index ->
                val recipe = recipesList[index]
                RecipeListGridItem(
                    recipe, modifier = Modifier.animateItem()
                ) {
                    onItemClick(recipe)
                }
            }
        }
        PullRefreshIndicator(
            modifier = Modifier.align(Alignment.TopCenter),
            refreshing = isRefreshing,
            state = pullRefreshState,
        )
    }
}

@Preview
@Composable
fun RecipesListGridPreview() {
    AndroidAppSampleTheme {
        RecipeListGrid(recommendedRecipesSamples, isRefreshing = false, modifier = Modifier, {}, {})
    }
}