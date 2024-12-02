package pe.com.master.machines.detail_recipe.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pe.com.master.machines.design.R
import pe.com.master.machines.design.ui.components.custom.NoteActions
import pe.com.master.machines.design.ui.components.dialogs.DialogAlert
import pe.com.master.machines.design.ui.components.dialogs.LoadingData
import pe.com.master.machines.design.ui.components.image.ImageWithUrl
import pe.com.master.machines.design.ui.components.text.CustomText
import pe.com.master.machines.design.ui.components.toolbars.ToolbarDetail
import pe.com.master.machines.design.ui.theme.ColorBlack
import pe.com.master.machines.design.ui.theme.ColorWhite
import pe.com.master.machines.design.ui.theme.ContentInset
import pe.com.master.machines.design.ui.theme.ContentInsetEight
import pe.com.master.machines.design.ui.theme.ContentInsetQuarter
import pe.com.master.machines.design.ui.theme.ContentInsetTwoHundredFifty
import pe.com.master.machines.design.ui.theme.DynamicTextFourteen
import pe.com.master.machines.design.ui.theme.DynamicTextSixteen
import pe.com.master.machines.design.ui.theme.Orange500
import pe.com.master.machines.design.ui.theme.notoSerifSemiBold
import pe.com.master.machines.detail_recipe.viewmodel.DetailRecipeViewmodel

@Composable
fun DetailRecipeScreen(
    recipeId: Int,
    navigateOnBack: () -> Unit,
    detailRecipeViewmodel: DetailRecipeViewmodel = hiltViewModel()
) {

    val colorTitle = MaterialTheme.colorScheme.primary
    val colorBody = MaterialTheme.colorScheme.secondary
    val tertiary = MaterialTheme.colorScheme.tertiary

    val initRecipe by detailRecipeViewmodel.initRecipe.collectAsStateWithLifecycle()
    val dataRecipe by detailRecipeViewmodel.dataRecipe.collectAsStateWithLifecycle()

    var messageDialogError by rememberSaveable { mutableStateOf("") }
    var messageLoading by rememberSaveable { mutableStateOf("") }

    val iconSaved by remember(dataRecipe.isSaved) { mutableStateOf(if (dataRecipe.isSaved) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder) }

    BackHandler {
        navigateOnBack()
    }

    LaunchedEffect(recipeId) {
        detailRecipeViewmodel.initRecipe(recipeId)
    }

    LaunchedEffect(initRecipe) {
        when (val state = initRecipe) {
            DetailRecipeState.Loading -> {
                messageLoading = "Cargando"
                messageDialogError = ""
            }

            is DetailRecipeState.Error -> {
                messageLoading = ""
                messageDialogError = state.throwable.message.orEmpty()
            }

            DetailRecipeState.Success -> {
                messageLoading = ""
                messageDialogError = ""
            }

            else -> {}
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize(), containerColor = ColorWhite, topBar = {
        ToolbarDetail(
            title = dataRecipe.nombre, onClickNavigation = navigateOnBack
        )
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.Transparent),
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max)
            ) {
                ImageWithUrl(
                    primaryColor = tertiary,
                    modifier = Modifier
                        .background(color = Color.Transparent)
                        .fillMaxWidth()
                        .height(ContentInsetTwoHundredFifty),
                    photoUrl = dataRecipe.imagen,
                    contentScale = ContentScale.Crop,
                    painterError = ""
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = ColorBlack.copy(alpha = 0.2f),
                        )
                )

                NoteActions(
                    modifier = Modifier
                        .padding(
                            top = ContentInsetQuarter, end = ContentInsetQuarter
                        )
                        .align(Alignment.TopEnd),
                    isSaved = dataRecipe.isSaved,
                    iconSaved = iconSaved,
                    onClickSavedNote = {
                        detailRecipeViewmodel.saveFavorite(dataRecipe)
                    },
                    onClickUnsavedNote = {
                        detailRecipeViewmodel.unSaveFavorite(dataRecipe.id)
                    },
                )
            }

            Spacer(modifier = Modifier.height(ContentInset))

            CustomText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = ContentInset),
                text = dataRecipe.descripcion,
                maxLines = 1,
                textColor = colorTitle,
                fontFamily = FontFamily(notoSerifSemiBold),
                fontSize = DynamicTextSixteen,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.height(ContentInset))

            CustomText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = ContentInset),
                text = "Ingredientes",
                isUnderlined = true,
                maxLines = 1,
                textColor = colorTitle,
                fontFamily = FontFamily(notoSerifSemiBold),
                fontSize = DynamicTextSixteen,
                fontWeight = FontWeight.Bold,
            )

            CustomText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = ContentInset),
                text = dataRecipe.ingredientes,
                maxLines = 50,
                textColor = colorBody,
                fontFamily = FontFamily(notoSerifSemiBold),
                fontSize = DynamicTextFourteen,
            )

            Spacer(modifier = Modifier.height(ContentInset))

            CustomText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = ContentInset),
                text = "Pasos",
                maxLines = 1,
                isUnderlined = true,
                textColor = colorTitle,
                fontFamily = FontFamily(notoSerifSemiBold),
                fontSize = DynamicTextSixteen,
                fontWeight = FontWeight.Bold,
            )

            CustomText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = ContentInset),
                text = dataRecipe.pasos,
                maxLines = 50,
                textColor = colorBody,
                fontFamily = FontFamily(notoSerifSemiBold),
                fontSize = DynamicTextFourteen,
            )
        }
    }

    if (messageDialogError.isNotEmpty()) {
        DialogAlert(title = "Error en el servidor",
            message = messageDialogError,
            isCancelable = true,
            textPositiveButton = stringResource(R.string.action_accept),
            textColorPositiveButton = Orange500,
            backgroundColorPositiveButton = ColorWhite,
            onPositiveCallback = { messageDialogError = "" },
            onDismissDialog = { messageDialogError = "" })
    }

    if (messageLoading.isNotEmpty()) LoadingData(message = messageLoading)

}