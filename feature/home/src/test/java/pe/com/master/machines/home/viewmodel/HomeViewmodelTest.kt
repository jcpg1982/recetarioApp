package pe.com.master.machines.home.viewmodel

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.spyk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import pe.com.master.machines.constants.http.ErrorType
import pe.com.master.machines.constants.http.Resource
import pe.com.master.machines.domain.local.database.SaveFavoriteUsesCase
import pe.com.master.machines.domain.local.database.UnSaveFavoriteUsesCase
import pe.com.master.machines.domain.remote.GetRecipesAllUsesCase
import pe.com.master.machines.home.screen.HomeState
import pe.com.master.machines.model.model.Recipe

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @RelaxedMockK
    private lateinit var getRecipesAllUsesCase: GetRecipesAllUsesCase

    @RelaxedMockK
    private lateinit var saveFavoriteUsesCase: SaveFavoriteUsesCase

    @RelaxedMockK
    private lateinit var unSaveFavoriteUsesCase: UnSaveFavoriteUsesCase

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var homeViewmodel: HomeViewmodel

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        homeViewmodel = spyk(
            HomeViewmodel(
                getRecipesAllUsesCase,
                saveFavoriteUsesCase,
                unSaveFavoriteUsesCase
            )
        )
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadAllRecipes should emit Success when getRecipesAllUsesCase returns data`() = runTest {
        val recipes = listOf(
            Recipe(id = 1, isSaved = false),
            Recipe(id = 2, isSaved = true)
        )
        val mockResult = Resource.Success(recipes)
        //given
        coEvery { getRecipesAllUsesCase.invoke() } returns flowOf(mockResult)
        // when
        homeViewmodel.loadAllRecipes()
        testDispatcher.scheduler.advanceUntilIdle()
        //then
        coVerify(exactly = 1) { homeViewmodel.loadAllRecipes() }
        val result = homeViewmodel.loadAllRecipes.value

        assertTrue(result is HomeState.Success)
    }

    @Test
    fun `loadAllRecipes should emit Error when getRecipesAllUsesCase returns 400 Not Found`() =
        runTest {
            val mockError = ErrorType.Api.NotFound("Resource not found")

            // given
            coEvery { getRecipesAllUsesCase.invoke() } returns flowOf(Resource.Error(mockError))

            // when
            homeViewmodel.loadAllRecipes()
            testDispatcher.scheduler.advanceUntilIdle()
            // then
            coVerify(exactly = 1) { homeViewmodel.loadAllRecipes() }
            val result = homeViewmodel.loadAllRecipes.value

            // Assert that the error is of type NotFound
            assertTrue(result is HomeState.Error)
            assertEquals("Resource not found", (result as HomeState.Error).throwable.message)
        }


    @Test
    fun `loadAllRecipes should emit Error when getRecipesAllUsesCase returns 500 Server Error`() =
        runTest {
            val mockError = ErrorType.Api.Server("Internal server error")

            // given
            coEvery { getRecipesAllUsesCase.invoke() } returns flowOf(Resource.Error(mockError))

            // when
            homeViewmodel.loadAllRecipes()
            testDispatcher.scheduler.advanceUntilIdle()
            // then
            coVerify(exactly = 1) { homeViewmodel.loadAllRecipes() }
            val result = homeViewmodel.loadAllRecipes.value

            // Assert that the error is of type Server
            assertTrue(result is HomeState.Error)
            assertEquals("Internal server error", (result as HomeState.Error).throwable.message)
        }


}
