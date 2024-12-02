package pe.com.master.machines.design.ui.components.dialogs

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import pe.com.master.machines.model.enums.DialogType

@Composable
fun DialogAlert(
    title: String,
    message: String,
    isCancelable: Boolean,
    textPositiveButton: String,
    textColorPositiveButton: Color,
    backgroundColorPositiveButton: Color,
    onPositiveCallback: () -> Unit,
    onDismissDialog: () -> Unit = {}
) {
    CustomDefaultDialog<Any>(title = title,
        message = message,
        dialogType = DialogType.ALERT,
        isCancelable = isCancelable,
        textPositiveButton = textPositiveButton,
        textColorPositiveButton = textColorPositiveButton,
        backgroundColorPositiveButton = backgroundColorPositiveButton,
        onPositiveCallback = { onPositiveCallback() },
        onNegativeCallback = { onDismissDialog() },
        onDismissDialog = { onDismissDialog() })
}

@Preview
@Composable
fun GetPreviewDialogAlert() {
   DialogAlert(title = "Error en el servidor",
        message = "Este es el cuerpo del dialogo",
        isCancelable = false,
        textPositiveButton = "Aceptar",
        textColorPositiveButton = Color.Blue,
        backgroundColorPositiveButton = Color.White,
        onPositiveCallback = { },
        onDismissDialog = {})
}