package com.jamescoggan.sample

import com.google.common.truth.Truth.assertThat
import com.jamescoggan.data.model.Recipe
import com.jamescoggan.data.model.Resource
import com.jamescoggan.data.repo.RecipesRepository
import com.jamescoggan.sample.recipes.RecipePresenter
import com.jamescoggan.sample.recipes.RecipesScreen
import com.slack.circuit.test.FakeNavigator
import com.slack.circuit.test.test
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class RecipesPresenterTest {
    private val navigator = FakeNavigator(RecipesScreen())


    @Test
    fun `present - emit refreshing state then no state`() = runTest {
        val repository = RecipesTestRepository(emptyList(), Resource.Loading())
        val presenter = RecipePresenter(navigator, repository)

        presenter.test {
            assertThat(awaitItem()).isEqualTo(RecipesScreen.State.Refreshing)
            assertThat(awaitItem()).isEqualTo(RecipesScreen.State.Empty)
        }
    }
}

class RecipesTestRepository(
    private val recipes: List<Recipe>, private val resource: Resource<Unit>
) : RecipesRepository {
    override fun refreshRecommended(): Flow<Resource<Unit>> = flow { emit(resource) }

    override fun recommendedFlow(): Flow<List<Recipe>> = flow { emit(recipes) }

}