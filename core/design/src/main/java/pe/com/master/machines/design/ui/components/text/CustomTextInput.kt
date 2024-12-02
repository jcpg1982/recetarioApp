package pe.com.master.machines.design.ui.components.text

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import pe.com.master.machines.design.ui.theme.ContentInset
import pe.com.master.machines.design.ui.theme.ContentInsetQuarter
import pe.com.master.machines.design.ui.theme.DynamicTextSixteen
import pe.com.master.machines.design.utils.Constants.Regex.ONLY_LETTERS

@Composable
fun CustomTextInput(
    modifier: Modifier,
    hintText: String,
    value: String,
    maxCharacter: Int,
    isEnabled: Boolean,
    isReadOnly: Boolean,
    keyboardType: KeyboardType,
    isErrorDisplayed: Boolean,
    messageError: String,
    maxLines: Int,
    regex: Regex,
    onTextValueChange: (String) -> Unit,
    onClickTextView: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    OutlinedTextField(
        value = value,
        onValueChange = {
            if (it.length <= maxCharacter && it.matches(regex = regex)) {
                onTextValueChange(it)
            }
        },
        modifier = modifier,
        enabled = isEnabled,
        readOnly = isReadOnly,
        label = {
            CustomText(
                text = hintText,
                overflow = TextOverflow.Ellipsis,
                minLines = 1,
                maxLines = 1,
                fontSize = DynamicTextSixteen,
            )
        },
        supportingText = {
            if (isErrorDisplayed) {
                CustomText(
                    modifier = Modifier.fillMaxWidth(),
                    text = messageError,
                    textColor = MaterialTheme.colorScheme.error,
                )
            }
        },
        trailingIcon = {
            if (isErrorDisplayed) {
                Icon(
                    imageVector = Icons.Filled.Error,
                    contentDescription = "error",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        },
        isError = isErrorDisplayed,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = maxLines == 1,
        minLines = 1,
        maxLines = maxLines,
        interactionSource = interactionSource
    )

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            if (interaction is PressInteraction.Release) {
                onClickTextView()
            }
        }
    }
}

@Preview
@Composable
fun GetPreviewCustomTextInput() {
    CustomTextInput(modifier = Modifier
        .fillMaxWidth()
        .padding(
            start = ContentInset, end = ContentInset, top = ContentInsetQuarter
        ),
        hintText = "ingrese un dato",
        value = "",
        maxCharacter = 50,
        isEnabled = true,
        isReadOnly = false,
        keyboardType = KeyboardType.Text,
        isErrorDisplayed = false,
        messageError = "",
        maxLines = 1,
        regex = ONLY_LETTERS,
        onTextValueChange = {},
        onClickTextView = {})
}