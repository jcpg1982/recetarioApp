package pe.com.master.machines.design.ui.components.text

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import pe.com.master.machines.design.ui.theme.BlueGray500
import pe.com.master.machines.design.ui.theme.DynamicTextFourteen
import pe.com.master.machines.design.ui.theme.DynamicTextSixteen
import pe.com.master.machines.design.ui.theme.robotoRegular

@Composable
fun SearchText(
    hintSearch: String,
    primaryColor: Color,
    colorText: Color,
    imeAction: ImeAction = ImeAction.Search,
    maxCharacter: Int = 50,
    onMessageSearch: (String) -> Unit
) {

    var filterName by rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = filterName,
        onValueChange = {
            if (it.length <= maxCharacter) {
                filterName = it
                onMessageSearch(filterName.lowercase())
            }
        },
        modifier = Modifier.fillMaxWidth(),
        textStyle = TextStyle(
            color = colorText,
            fontSize = DynamicTextFourteen,
            fontFamily = FontFamily(robotoRegular)
        ),
        label = {
            CustomText(
                text = hintSearch,
                fontSize = DynamicTextSixteen,
                textColor = colorText.copy(alpha = 0.5f)
            )
        }, leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "open drop down",
                tint = BlueGray500
            )
        }, trailingIcon = {
            if (filterName.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "clear data",
                    modifier = Modifier.clickable {
                        filterName = ""
                        onMessageSearch("")
                    },
                    tint = BlueGray500
                )
            }
        }, keyboardOptions = KeyboardOptions(
            imeAction = imeAction
        ), keyboardActions = KeyboardActions(
            onSearch = { keyboardController?.hide() }
        ), minLines = 1,
        maxLines = 1,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            unfocusedBorderColor = primaryColor,
            focusedBorderColor = primaryColor
        )
    )
}

@Preview
@Composable
fun GetPreviewSearchText() {
    SearchText(
        hintSearch = "Buscar", maxCharacter = 10, primaryColor = Color.Red, colorText = Color.Black
    ) { }
}