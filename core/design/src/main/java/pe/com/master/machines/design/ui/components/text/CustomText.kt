package pe.com.master.machines.design.ui.components.text

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import pe.com.master.machines.design.ui.theme.BlueGray800
import pe.com.master.machines.design.ui.theme.ContentInsetEight
import pe.com.master.machines.design.ui.theme.DynamicTextFourteen
import pe.com.master.machines.design.ui.theme.robotoRegular

@Composable
fun CustomText(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = BlueGray800,
    fontSize: TextUnit = DynamicTextFourteen,
    minLines: Int = 1,
    maxLines: Int = 3,
    fontWeight: FontWeight = FontWeight.Normal,
    textAlign: TextAlign = TextAlign.Start,
    isUnderlined: Boolean = false,
    fontFamily: FontFamily = FontFamily(robotoRegular),
    overflow: TextOverflow = TextOverflow.Ellipsis,
    onTextLayout: (TextLayoutResult) -> Unit = {}
) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = fontSize,
        color = textColor,
        textAlign = textAlign,
        lineHeight = fontSize * 1.3,
        maxLines = maxLines,
        minLines = minLines,
        fontWeight = fontWeight,
        overflow = overflow,
        style = TextStyle(
            textDecoration = if (isUnderlined) TextDecoration.Underline else TextDecoration.None
        ),
        fontFamily = fontFamily,
        onTextLayout = onTextLayout
    )
}

@Preview
@Composable
fun PreviewCustomText(modifier: Modifier = Modifier) {
    Column {
        CustomText(text = "Error", isUnderlined = true)
        Spacer(modifier = Modifier.height(ContentInsetEight))
        CustomText(text = "Error", isUnderlined = false)
    }
}
