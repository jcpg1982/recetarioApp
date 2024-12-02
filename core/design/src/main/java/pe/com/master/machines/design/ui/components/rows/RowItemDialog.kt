package pe.com.master.machines.design.ui.components.rows

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import pe.com.master.machines.design.ui.theme.BlueGray600
import pe.com.master.machines.design.ui.theme.ContentInsetQuarter
import pe.com.master.machines.design.ui.theme.DynamicTextFourteen

@Composable
fun RowItemDialog(text: String, color: Color, onItemsCallback: () -> Unit) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = ContentInsetQuarter)
            .clickable { onItemsCallback.invoke() },
        color = color,
        fontSize = DynamicTextFourteen,
        maxLines = 1,
        minLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}

@Preview
@Composable
fun GetPreviewRowItemDialog(modifier: Modifier = Modifier) {
    RowItemDialog(text = "texto ingresado", color = BlueGray600) { }
}