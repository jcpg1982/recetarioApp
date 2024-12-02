package pe.com.master.machines.design.ui.components.rows

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import pe.com.master.machines.design.ui.components.custom.NoteActions
import pe.com.master.machines.design.ui.components.image.ImageWithUrl
import pe.com.master.machines.design.ui.components.text.CustomText
import pe.com.master.machines.design.ui.theme.ColorBlack
import pe.com.master.machines.design.ui.theme.ContentInsetEight
import pe.com.master.machines.design.ui.theme.ContentInsetOneHundred
import pe.com.master.machines.design.ui.theme.ContentInsetQuarter
import pe.com.master.machines.design.ui.theme.DynamicTextEighteen
import pe.com.master.machines.design.ui.theme.DynamicTextSixteen
import pe.com.master.machines.design.ui.theme.notoSerifSemiBold
import pe.com.master.machines.model.model.Recipe

@Composable
fun RowRecipeLeft(
    item: Recipe,
    painterError: String,
    modifier: Modifier = Modifier,
    onClickSavedNote: () -> Unit,
    onClickUnsavedNote: () -> Unit,
    onClickSharedNote: () -> Unit = {},
) {
    val colorTitle = MaterialTheme.colorScheme.primary
    val colorBody = MaterialTheme.colorScheme.secondary
    val tertiary = MaterialTheme.colorScheme.tertiary

    val iconSaved by remember(item.isSaved) { mutableStateOf(if (item.isSaved) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder) }

    Row(modifier = modifier.height(IntrinsicSize.Max)) {

        Column(
            modifier = Modifier
                .padding(end = ContentInsetEight)
                .fillMaxWidth()
                .weight(1f)
        ) {
            CustomText(
                text = item.nombre,
                maxLines = 1,
                textColor = colorTitle,
                fontFamily = FontFamily(notoSerifSemiBold),
                fontSize = DynamicTextEighteen,
                fontWeight = FontWeight.Bold,
            )
            CustomText(
                text = item.descripcion,
                maxLines = 6,
                textColor = colorBody,
                fontFamily = FontFamily(notoSerifSemiBold),
                fontSize = DynamicTextSixteen,
            )

            Spacer(modifier = Modifier.weight(1f))
        }

        Box(
            modifier = Modifier
                .width(IntrinsicSize.Max)
                .height(IntrinsicSize.Max)
        ) {
            ImageWithUrl(
                primaryColor = tertiary,
                modifier = Modifier
                    .background(
                        color = Color.Transparent, shape = RoundedCornerShape(ContentInsetEight)
                    )
                    .size(ContentInsetOneHundred)
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(ContentInsetEight)),
                photoUrl = item.imagen,
                contentScale = ContentScale.Crop,
                painterError = painterError
            )

            Spacer(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = ColorBlack.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(ContentInsetEight)
                    )
            )

            NoteActions(
                modifier = Modifier
                    .padding(top = ContentInsetQuarter, end = ContentInsetQuarter)
                    .align(Alignment.TopEnd),
                isSaved = item.isSaved,
                iconSaved = iconSaved,
                onClickSavedNote = onClickSavedNote,
                onClickUnsavedNote = onClickUnsavedNote,
                onClickSharedNote = onClickSharedNote
            )
        }
    }
}

@Preview
@Composable
fun GetPreviewRowOpinionLeft() {
    Column {
        RowRecipeLeft(
            item = Recipe(),
            painterError = "",
            onClickSavedNote = {},
            onClickUnsavedNote = {},
            onClickSharedNote = {},
        )
        Spacer(modifier = Modifier.height(ContentInsetEight))
        RowRecipeLeft(
            item = Recipe(),
            painterError = "",
            onClickSavedNote = {},
            onClickUnsavedNote = {},
            onClickSharedNote = {},
        )
    }
}