package pe.com.master.machines.design.ui.components.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pe.com.master.machines.design.R
import pe.com.master.machines.design.ui.theme.ContentInsetOneHundred
import pe.com.master.machines.design.ui.theme.ContentInsetOneHundredFifty

@Composable
fun ImageLoading(
    primaryColor: Color,
    contentScale: ContentScale = ContentScale.Fit,
) {

    Box(
        modifier = Modifier, contentAlignment = Alignment.Center
    ) {

        CircularProgressIndicator(
            modifier = Modifier.size(ContentInsetOneHundredFifty),
            strokeWidth = 10.dp,
            color = primaryColor
        )

        Image(
            painter = painterResource(id = R.drawable.loading),
            contentDescription = "",
            modifier = Modifier
                .size(ContentInsetOneHundred)
                .clip(CircleShape),
            contentScale = contentScale,
        )
    }
}

@Composable
@Preview
fun PreviewImageLoading() {
    ImageLoading(primaryColor = Color.Blue)
}