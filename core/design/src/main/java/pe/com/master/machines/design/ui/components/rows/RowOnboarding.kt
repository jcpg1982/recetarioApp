package pe.com.master.machines.design.ui.components.rows

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import pe.com.master.machines.design.ui.theme.ContentInset
import pe.com.master.machines.design.ui.theme.Red500
import pe.com.master.machines.design.ui.theme.Turquoise200
import pe.com.master.machines.design.utils.Utils.listImageOnBoarding

@Composable
fun RowOnboarding(page: Int, onBackItem: () -> Unit, onNextItem: () -> Unit) {

    val photo by remember(page) { mutableStateOf(listImageOnBoarding[page]) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {

        Image(
            painter = painterResource(id = photo),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            if (page > 0) {
                IconButton(
                    modifier = Modifier
                        .padding(ContentInset)
                        .background(color = Turquoise200, shape = CircleShape)
                        .clip(CircleShape),
                    onClick = { onBackItem() }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = Red500,
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            IconButton(
                modifier = Modifier
                    .padding(ContentInset)
                    .background(color = Turquoise200, shape = CircleShape)
                    .clip(CircleShape),
                onClick = { onNextItem() }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = Red500,
                )
            }

        }

    }
}

@Preview
@Composable
fun PreviewRowOnboarding() {
    RowOnboarding(-1, {}, {})
}