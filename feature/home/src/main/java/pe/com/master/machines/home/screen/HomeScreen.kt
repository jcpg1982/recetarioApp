package pe.com.master.machines.home.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import pe.com.master.machines.design.R
import pe.com.master.machines.design.ui.components.dialogs.DialogAlert
import pe.com.master.machines.design.ui.components.dialogs.DialogConfirm
import pe.com.master.machines.design.ui.components.dialogs.LoadingData
import pe.com.master.machines.design.ui.components.rows.RowOnboarding
import pe.com.master.machines.design.ui.components.rows.RowRecipeLeft
import pe.com.master.machines.design.ui.components.rows.RowRecipeRight
import pe.com.master.machines.design.ui.components.toolbars.ToolbarHome
import pe.com.master.machines.design.ui.theme.BlueGray200
import pe.com.master.machines.design.ui.theme.ColorWhite
import pe.com.master.machines.design.ui.theme.ContentInset
import pe.com.master.machines.design.ui.theme.ContentInsetEight
import pe.com.master.machines.design.ui.theme.ContentInsetTwo
import pe.com.master.machines.design.ui.theme.Orange500
import pe.com.master.machines.design.ui.theme.Red500
import pe.com.master.machines.design.ui.theme.Turquoise500
import pe.com.master.machines.design.utils.Utils.listImageOnBoarding
import pe.com.master.machines.home.viewmodel.HomeViewmodel
import kotlin.system.exitProcess

@Composable
fun HomeScreen(
    navigateToDetailRecipe: (Int) -> Unit,
    homeViewmodel: HomeViewmodel = hiltViewModel()
) {

    val loadAllRecipes by homeViewmodel.loadAllRecipes.collectAsStateWithLifecycle()
    val dataList by homeViewmodel.dataList.collectAsStateWithLifecycle()
    val initFirst by homeViewmodel.initFirst.collectAsStateWithLifecycle()

    val title by remember(initFirst) { mutableStateOf(if (initFirst) "" else "Inicio") }
    var exitApp by rememberSaveable { mutableStateOf("") }
    var messageDialogError by rememberSaveable { mutableStateOf("") }
    var messageLoading by rememberSaveable { mutableStateOf("") }

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = { listImageOnBoarding.size })
    val scope = rememberCoroutineScope()

    BackHandler {
        exitApp = "Desea Salir de la Aplicaciión"
    }

    LaunchedEffect(loadAllRecipes) {
        when (val state = loadAllRecipes) {
            HomeState.Loading -> {
                messageLoading = "Cargando"
                messageDialogError = ""
            }

            is HomeState.Error -> {
                messageLoading = ""
                messageDialogError = state.throwable.message.orEmpty()
            }

            HomeState.Success -> {
                messageLoading = ""
                messageDialogError = ""
            }

            else -> {}
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = ColorWhite,
        topBar = {
            ToolbarHome(title = title)
        }
    ) { paddingValues ->
        if (initFirst) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .background(color = Color.Transparent)
                    .fillMaxSize()
            ) { page ->
                RowOnboarding(
                    page = page,
                    onBackItem = {
                        scope.launch {
                            pagerState.scrollToPage(page - 1)
                        }
                    },
                    onNextItem = {
                        if (page == listImageOnBoarding.size - 1) {
                            homeViewmodel.initFirst(false)
                        }else{
                            scope.launch {
                                pagerState.scrollToPage(page + 1)
                            }
                        }
                    }
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = ContentInset)
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color.Transparent),
                verticalArrangement = Arrangement.spacedBy(ContentInsetEight)
            ) {

                itemsIndexed(dataList) { index, item ->
                    if (index % 2 == 0) {
                        RowRecipeRight(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { navigateToDetailRecipe(item.id) },
                            item = item,
                            painterError = "",
                            onClickSavedNote = {
                                homeViewmodel.saveFavorite(item)
                            },
                            onClickUnsavedNote = {
                                homeViewmodel.unSaveFavorite(item.id)
                            },
                        )
                    } else {
                        RowRecipeLeft(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { navigateToDetailRecipe(item.id) },
                            item = item,
                            painterError = "",
                            onClickSavedNote = {
                                homeViewmodel.saveFavorite(item)
                            },
                            onClickUnsavedNote = {
                                homeViewmodel.unSaveFavorite(item.id)
                            },
                        )
                    }
                    if (index != dataList.size - 1) {
                        Spacer(
                            modifier = Modifier
                                .height(ContentInsetTwo)
                                .background(color = BlueGray200)
                        )
                    }
                }

            }
        }
    }

    if (exitApp.isNotBlank()) {
        DialogConfirm(title = "Salir de la aplicación",
            message = exitApp,
            isCancelable = false,
            textPositiveButton = "Salir",
            textColorPositiveButton = Red500,
            backgroundColorPositiveButton = ColorWhite,
            onPositiveCallback = {
                exitApp = ""
                exitProcess(0)
            },
            onDismissDialog = { exitApp = "" },
            textNegativeButton = "Continuar",
            textColorNegativeButton = Turquoise500,
            backgroundColorNegativeButton = ColorWhite,
            onNegativeCallback = { exitApp = "" })
    }

    if (messageDialogError.isNotEmpty()) {
        DialogAlert(
            title = "Error en el servidor",
            message = messageDialogError,
            isCancelable = true,
            textPositiveButton = stringResource(R.string.action_accept),
            textColorPositiveButton = Orange500,
            backgroundColorPositiveButton = ColorWhite,
            onPositiveCallback = { messageDialogError = "" },
            onDismissDialog = { messageDialogError = "" }
        )
    }

    if (messageLoading.isNotEmpty()) LoadingData(message = messageLoading)

}