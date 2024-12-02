package pe.com.master.machines.design.ui.components.dialogs

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import pe.com.master.machines.design.ui.theme.ColorWhite
import pe.com.master.machines.design.ui.theme.Red500
import pe.com.master.machines.design.ui.theme.Turquoise500
import pe.com.master.machines.model.enums.DialogType

@Composable
fun DialogConfirm(
    title: String,
    message: String,
    isCancelable: Boolean,
    textPositiveButton: String,
    textColorPositiveButton: Color,
    backgroundColorPositiveButton: Color,
    onPositiveCallback: () -> Unit,
    textNegativeButton: String,
    textColorNegativeButton: Color,
    backgroundColorNegativeButton: Color,
    onNegativeCallback: () -> Unit,
    onDismissDialog: () -> Unit = {}
) {
    CustomDefaultDialog<Any>(title = title,
        message = message,
        dialogType = DialogType.CONFIRM,
        isCancelable = isCancelable,
        textPositiveButton = textPositiveButton,
        textColorPositiveButton = textColorPositiveButton,
        backgroundColorPositiveButton = backgroundColorPositiveButton,
        onPositiveCallback = { onPositiveCallback() },
        textNegativeButton = textNegativeButton,
        textColorNegativeButton = textColorNegativeButton,
        backgroundColorNegativeButton = backgroundColorNegativeButton,
        onNegativeCallback = { onNegativeCallback() },
        onDismissDialog = { onDismissDialog() })
}

@Preview
@Composable
fun PreviewDialogConfirm() {
    DialogConfirm(
        title = "titulo",
        message = "mensaje",
        isCancelable = false,
        textPositiveButton = "aceptar",
        textColorPositiveButton = Red500,
        backgroundColorPositiveButton = ColorWhite,
        onPositiveCallback = { },
        textNegativeButton = "cancelar",
        textColorNegativeButton = Turquoise500,
        backgroundColorNegativeButton = ColorWhite,
        onNegativeCallback = { }) {

    }
}