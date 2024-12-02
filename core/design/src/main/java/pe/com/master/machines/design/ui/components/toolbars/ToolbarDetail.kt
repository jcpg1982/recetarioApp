package pe.com.master.machines.design.ui.components.toolbars

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import pe.com.master.machines.design.ui.components.text.CustomText
import pe.com.master.machines.design.ui.theme.ColorBlack
import pe.com.master.machines.design.ui.theme.DynamicTextTwentyTwo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolbarDetail(
    modifier: Modifier = Modifier,
    title: String,
    colorBackground: Color = Color.Transparent,
    colorTextAndIcon: Color = ColorBlack,
    onClickNavigation: () -> Unit ,
) {

    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        colors = TopAppBarColors(
            containerColor = colorBackground,
            scrolledContainerColor = Color.Black,
            navigationIconContentColor = Color.Black,
            titleContentColor = Color.Gray,
            actionIconContentColor = Color.Black
        ),
        title = {
            CustomText(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                textColor = colorTextAndIcon,
                fontSize = DynamicTextTwentyTwo
            )
        },
        navigationIcon = {
            IconButton(onClick = { onClickNavigation() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = colorTextAndIcon
                )
            }
        }
    )
}

@Preview
@Composable
fun PreviewToolbarDetail() {
    ToolbarDetail(
        title = "Titulo",
        onClickNavigation={}
    )
}