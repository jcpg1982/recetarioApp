package pe.com.master.machines.design.ui.components.dialogs

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import pe.com.master.machines.design.R
import pe.com.master.machines.design.ui.components.text.CustomText
import pe.com.master.machines.design.ui.theme.ColorWhite
import pe.com.master.machines.design.ui.theme.ContentInset
import pe.com.master.machines.design.ui.theme.ContentInsetOneHundred
import pe.com.master.machines.design.ui.theme.ContentInsetOneHundredFifty
import pe.com.master.machines.design.ui.theme.ContentInsetQuarter
import pe.com.master.machines.design.ui.theme.DynamicTextEighteen
import pe.com.master.machines.design.ui.theme.Turquoise500

@Composable
fun LoadingData(message: String) {
    Dialog(
        onDismissRequest = { }, properties = DialogProperties(
            dismissOnBackPress = false, dismissOnClickOutside = false
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.wrapContentSize()) {
                CircularProgressIndicator(
                    color = Turquoise500,
                    strokeWidth = ContentInsetQuarter,
                    modifier = Modifier
                        .size(ContentInsetOneHundredFifty)
                        .align(Alignment.Center)
                )
                Image(
                    painter = painterResource(id = R.drawable.loading),
                    contentDescription = "Image Loading",
                    modifier = Modifier
                        .size(ContentInsetOneHundred)
                        .align(Alignment.Center)
                        .clip(CircleShape),
                    alignment = Alignment.Center
                )
            }

            CustomText(
                text = message,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = ContentInset, end = ContentInset, top = ContentInset)
                    .align(Alignment.CenterHorizontally),
                textColor = ColorWhite,
                fontSize = DynamicTextEighteen,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun PreviewLoading() {
    LoadingData(message = "Cargando")
}