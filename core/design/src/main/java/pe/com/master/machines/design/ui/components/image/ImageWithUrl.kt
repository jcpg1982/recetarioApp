package pe.com.master.machines.design.ui.components.image

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import pe.com.master.machines.design.ui.theme.ContentInsetOneHundredFifty
import pe.com.master.machines.design.utils.Utils.getModelImage

@Composable
fun ImageWithUrl(
    photoUrl: String,
    primaryColor: Color,
    modifier: Modifier = Modifier,
    painterError: String = "",
    contentScale: ContentScale = ContentScale.Fit,
    colorIcon: Color = Color.Blue,
    isChangeColorIcon: Boolean = false,
    bitmapImage: Bitmap? = null
) {

    val context = LocalContext.current
    val density = LocalDensity.current.density
    var widthInDp by remember { mutableFloatStateOf(value = 0f) }
    var heightInDp by remember { mutableFloatStateOf(value = 0f) }

    if (bitmapImage != null) {
        Image(
            bitmap = bitmapImage.asImageBitmap(),
            contentDescription = null,
            modifier = modifier,
            contentScale = contentScale
        )
    } else {
        SubcomposeAsyncImage(
            model = getModelImage(context, photoUrl),
            contentDescription = "",
            modifier = modifier.onGloballyPositioned { coordinates ->
                val size = coordinates.size
                widthInDp = size.width.toFloat() / density
                heightInDp = size.height.toFloat() / density
            },
            loading = {
                val indicatorSize = (widthInDp.coerceAtMost(heightInDp) * 0.9).dp
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        color = primaryColor,
                        modifier = Modifier
                            .size(indicatorSize)
                            .align(Alignment.Center),
                        strokeWidth = (indicatorSize * 0.03f).coerceAtLeast(1.dp),
                    )
                }
            },
            error = {
                SubcomposeAsyncImage(
                    model = getModelImage(context, painterError),
                    contentDescription = "Error",
                    modifier = Modifier.fillMaxSize(),
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Fit
                )
            },
            success = {
                if (isChangeColorIcon) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        this@SubcomposeAsyncImage.SubcomposeAsyncImageContent(
                            modifier = Modifier.align(Alignment.Center),
                            colorFilter = ColorFilter.tint(colorIcon)
                        )
                    }
                } else {
                    SubcomposeAsyncImageContent()
                }
            },
            alignment = Alignment.Center,
            contentScale = contentScale
        )
    }
}


@Composable
@Preview
fun PreviewImageWithUrl() {
    ImageWithUrl(
        photoUrl = "",
        primaryColor = Color.Blue,
        painterError = "",
        isChangeColorIcon = true,
        colorIcon = Color.White,
        modifier = Modifier
            .background(color = Color.Transparent, shape = RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .height(ContentInsetOneHundredFifty),
    )
}