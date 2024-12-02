package pe.com.master.machines.recetario.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import pe.com.master.machines.design.utils.Utils.backEnterTransition
import pe.com.master.machines.design.utils.Utils.backExitTransition
import pe.com.master.machines.design.utils.Utils.forwardEnterTransition
import pe.com.master.machines.design.utils.Utils.forwardExitTransition
import pe.com.master.machines.detail_recipe.navigation.DetailRecipeRoute
import pe.com.master.machines.detail_recipe.screen.DetailRecipeScreen
import pe.com.master.machines.home.navigation.HomeRoute
import pe.com.master.machines.home.screen.HomeScreen
import pe.com.master.machines.splash.navigation.SplashRoute
import pe.com.master.machines.splash.screen.SplashScreen

@Composable
fun NavigationWrapper(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = SplashRoute,
        enterTransition = { forwardEnterTransition },
        exitTransition = { forwardExitTransition },
        popEnterTransition = { backEnterTransition },
        popExitTransition = { backExitTransition }
    ) {

        composable<SplashRoute> {
            SplashScreen(
                navigateToHome = {
                    navController.navigate(HomeRoute) {
                        popUpTo<SplashRoute> { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<HomeRoute> {
            HomeScreen(
                navigateToDetailRecipe = { recipeId ->
                    navController.navigate(DetailRecipeRoute(recipeId))
                }
            )
        }

        composable<DetailRecipeRoute> { backStackEntry ->
            val detail = backStackEntry.toRoute<DetailRecipeRoute>()
            DetailRecipeScreen(
                recipeId = detail.recipeId,
                navigateOnBack = navController::popBackStack,
            )
        }
    }

}