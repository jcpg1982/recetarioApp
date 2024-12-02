package pe.com.master.machines.design.ui.components.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import pe.com.master.machines.design.ui.components.list.LazyColumnCustom
import pe.com.master.machines.design.ui.components.text.CustomText
import pe.com.master.machines.design.ui.components.text.CustomTextInput
import pe.com.master.machines.design.ui.components.text.MessageDialog
import pe.com.master.machines.design.ui.components.text.SearchText
import pe.com.master.machines.design.ui.components.text.TextClickButton
import pe.com.master.machines.design.ui.components.text.TitleDialog
import pe.com.master.machines.design.ui.theme.BlueGray300
import pe.com.master.machines.design.ui.theme.ColorBlack
import pe.com.master.machines.design.ui.theme.ColorWhite
import pe.com.master.machines.design.ui.theme.ContentInset
import pe.com.master.machines.design.ui.theme.ContentInsetQuarter
import pe.com.master.machines.design.ui.theme.DynamicTextEighteen
import pe.com.master.machines.design.utils.Constants.Regex.ONLY_LETTERS
import pe.com.master.machines.model.enums.DialogType

@Preview
@Composable
fun PreviewDialogDefault() {
    CustomDefaultDialog<String>(title = "Error en el servidor",
        message = "Este es el cuerpo del dialogo",
        dialogType = DialogType.CONFIRM,
        isCancelable = false,
        primaryColor = Color.Red,
        hintSearch = "Search country",
        hintOthers = null,
        listItems = listOf(
            "Item 1",
            "Item 2",
            "Item 3",
            "Item 4",
            "Item 5",
            "Item 6",
        ),
        textPositiveButton = "Aceptar",
        textColorPositiveButton = Color.Blue,
        backgroundColorPositiveButton = Color.White,
        textNegativeButton = null,
        textColorNegativeButton = null,
        backgroundColorNegativeButton = null,
        onPositiveCallback = { },
        onDismissDialog = {},
        onNegativeCallback = { },
        onItemsCallback = { pos, data -> },
        onInputCallback = { },
        onInputSearchCallback = { })
}

@Composable
inline fun <reified T> CustomDefaultDialog(
    title: String? = null,
    message: String? = null,
    dialogType: DialogType,
    isCancelable: Boolean,
    primaryColor: Color? = null,
    colorText: Color = ColorBlack,
    hintSearch: String? = null,
    hintOthers: String? = null,
    listItems: List<T>? = null,
    textPositiveButton: String? = null,
    textColorPositiveButton: Color? = null,
    backgroundColorPositiveButton: Color? = null,
    crossinline onPositiveCallback: () -> Unit = {},
    textNegativeButton: String? = null,
    textColorNegativeButton: Color? = null,
    backgroundColorNegativeButton: Color? = null,
    crossinline onNegativeCallback: () -> Unit = {},
    crossinline onItemsCallback: (Int, T) -> Unit = { pos, data -> },
    crossinline onInputCallback: (String) -> Unit = {},
    crossinline onInputSearchCallback: () -> Unit = {},
    crossinline onDismissDialog: () -> Unit = {},
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    Dialog(
        onDismissRequest = { onDismissDialog() }, properties = DialogProperties(
            dismissOnBackPress = isCancelable, dismissOnClickOutside = isCancelable
        )
    ) {
        OutlinedCard(
            modifier = Modifier
                .widthIn(min = screenWidth * 0.20f, max = screenWidth * 0.75f)
                .clip(RoundedCornerShape(ContentInset))
        ) {
            when (dialogType) {
                DialogType.ALERT -> {
                    title?.let { t ->
                        message?.let { m ->
                            textPositiveButton?.let { tpb ->
                                textColorPositiveButton?.let { tcpb ->
                                    backgroundColorPositiveButton?.let { bcpb ->
                                        Alert(
                                            title = t,
                                            message = m,
                                            textPositiveButton = tpb,
                                            textColorPositiveButton = tcpb,
                                            backgroundColorPositiveButton = bcpb
                                        ) { onPositiveCallback() }
                                    }
                                }
                            }
                        }
                    }
                }

                DialogType.CONFIRM -> {
                    title?.let { t ->
                        message?.let { m ->
                            textPositiveButton?.let { tpb ->
                                textColorPositiveButton?.let { tcpb ->
                                    backgroundColorPositiveButton?.let { bcpb ->
                                        textNegativeButton?.let { tnb ->
                                            textColorNegativeButton?.let { tcnb ->
                                                backgroundColorNegativeButton?.let { bcnb ->
                                                    Confirm(title = t,
                                                        message = m,
                                                        textPositiveButton = tpb,
                                                        textColorPositiveButton = tcpb,
                                                        backgroundColorPositiveButton = bcpb,
                                                        onPositiveCallback = { onPositiveCallback() },
                                                        textNegativeButton = tnb,
                                                        textColorNegativeButton = tcnb,
                                                        backgroundColorNegativeButton = bcnb,
                                                        onNegativeCallback = { onNegativeCallback() })
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                DialogType.OTHERS -> {}
                DialogType.ITEMS -> {
                    title?.let { t ->
                        listItems?.let { li ->
                            Items(title = t, listItems = li) { pos, item ->
                                onItemsCallback(pos, item)
                            }
                        }
                    }
                }

                DialogType.ITEMS_OTHERS -> {
                    title?.let { t ->
                        listItems?.let { li ->
                            hintOthers?.let { h ->
                                textPositiveButton?.let { tpb ->
                                    textColorPositiveButton?.let { tcpb ->
                                        backgroundColorPositiveButton?.let { bcpb ->
                                            ItemOthers(title = t,
                                                listItems = li,
                                                hintOthers = h,
                                                textPositiveButton = tpb,
                                                textColorPositiveButton = tcpb,
                                                backgroundColorPositiveButton = bcpb,
                                                onPositiveCallback = { onPositiveCallback.invoke() },
                                                onItemsCallback = { pos, data ->
                                                    onItemsCallback(pos, data)
                                                },
                                                onInputCallback = { onInputCallback(it) })
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                DialogType.ITEMS_SEARCH -> {
                    hintSearch?.let { hs ->
                        listItems?.let { li ->
                            ItemsSearch(
                                hintSearch = hs,
                                listItems = li,
                                primaryColor = primaryColor,
                                colorText = colorText,
                                onItemsCallback = { pos, item ->
                                    onItemsCallback(pos, item)
                                })
                        }
                    }
                }

                DialogType.ITEMS_OTHERS_AND_SEARCH -> {}
                DialogType.ITEMS_ALERT -> {
                    title?.let { t ->
                        message?.let { m ->
                            listItems?.let { li ->
                                textPositiveButton?.let { tpb ->
                                    textColorPositiveButton?.let { tcpb ->
                                        backgroundColorPositiveButton?.let { bpb ->
                                            ItemsAlert(title = t,
                                                message = m,
                                                listItems = li,
                                                textPositiveButton = tpb,
                                                textColorPositiveButton = tcpb,
                                                backgroundColorPositiveButton = bpb,
                                                onPositiveCallback = { onPositiveCallback() },
                                                onItemsCallback = { pos, data ->
                                                    onItemsCallback(pos, data)
                                                })
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Alert(
    title: String,
    message: String,
    textPositiveButton: String,
    textColorPositiveButton: Color,
    backgroundColorPositiveButton: Color,
    onPositiveCallback: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = ColorWhite)
            .padding(ContentInset)
    ) {
        TitleDialog(title = title)
        MessageDialog(message = message)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = ContentInset),
            contentAlignment = Alignment.CenterEnd
        ) {
            TextClickButton(
                modifier = Modifier.wrapContentWidth(),
                textButton = textPositiveButton,
                textColorButton = textColorPositiveButton,
                backgroundColorButton = backgroundColorPositiveButton
            ) { onPositiveCallback() }
        }
    }
}

@Composable
fun Confirm(
    title: String,
    message: String,
    textPositiveButton: String,
    textColorPositiveButton: Color,
    backgroundColorPositiveButton: Color,
    onPositiveCallback: () -> Unit,
    textNegativeButton: String,
    textColorNegativeButton: Color,
    backgroundColorNegativeButton: Color,
    onNegativeCallback: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = ColorWhite)
            .padding(ContentInset)
    ) {
        TitleDialog(title = title)
        MessageDialog(message = message)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = ContentInset),
            horizontalArrangement = Arrangement.End
        ) {
            TextClickButton(
                modifier = Modifier.wrapContentWidth(),
                textButton = textNegativeButton,
                textColorButton = textColorNegativeButton,
                backgroundColorButton = backgroundColorNegativeButton
            ) { onNegativeCallback() }
            Spacer(modifier = Modifier.width(ContentInset))
            TextClickButton(
                modifier = Modifier.wrapContentWidth(),
                textButton = textPositiveButton,
                textColorButton = textColorPositiveButton,
                backgroundColorButton = backgroundColorPositiveButton
            ) { onPositiveCallback() }
        }
    }
}

@Composable
fun Others() {
}

@Composable
inline fun <reified T> Items(
    title: String, listItems: List<T>, crossinline onItemsCallback: (Int, T) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = ColorWhite)
            .padding(ContentInset)
    ) {
        TitleDialog(title = title)
        LazyColumnCustom(listItems = listItems,
            onItemsCallback = { pos, data -> onItemsCallback(pos, data) })
    }
}

@Composable
inline fun <reified T> ItemOthers(
    title: String,
    listItems: List<T>,
    hintOthers: String,
    textPositiveButton: String,
    textColorPositiveButton: Color,
    backgroundColorPositiveButton: Color,
    crossinline onPositiveCallback: () -> Unit,
    crossinline onItemsCallback: (Int, T) -> Unit,
    crossinline onInputCallback: (String) -> Unit
) {
    var others by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = ColorWhite)
            .padding(ContentInset)
    ) {
        TitleDialog(title = title)
        LazyColumnCustom(
            listItems = listItems,
            onItemsCallback = { pos, data -> onItemsCallback(pos, data) }
        )
        CustomTextInput(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = ContentInsetQuarter),
            hintText = hintOthers,
            value = others,
            maxCharacter = 10,
            isEnabled = true,
            isReadOnly = false,
            keyboardType = KeyboardType.Text,
            isErrorDisplayed = false,
            messageError = "",
            maxLines = 1,
            regex = ONLY_LETTERS,
            onTextValueChange = {
                others = it
                onInputCallback(others)
            },
            onClickTextView = {}
        )
        if (others.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = ContentInset),
                contentAlignment = Alignment.CenterEnd
            ) {
                TextClickButton(
                    modifier = Modifier.wrapContentWidth(),
                    textButton = textPositiveButton,
                    textColorButton = textColorPositiveButton,
                    backgroundColorButton = backgroundColorPositiveButton
                ) { onPositiveCallback() }
            }
        }
    }
}

@Composable
inline fun <reified T> ItemsSearch(
    hintSearch: String,
    listItems: List<T>,
    primaryColor: Color?,
    colorText: Color,
    crossinline onItemsCallback: (Int, T) -> Unit
) {
    var filteredList by remember { mutableStateOf(listItems) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = ColorWhite)
            .padding(ContentInset)
    ) {
        SearchText(
            hintSearch = hintSearch,
            maxCharacter = 10,
            primaryColor = primaryColor ?: BlueGray300,
            colorText = colorText,
            onMessageSearch = {
                filteredList = if (it.isEmpty()) {
                    listItems
                } else {
                    listItems.filter { data ->
                        when (data) {
                            else -> {
                                data.toString().lowercase().contains(it)
                            }
                        }
                    }
                }
            }
        )
        LazyColumnCustom(
            listItems = filteredList,
            onItemsCallback = { pos, data -> onItemsCallback(pos, data) }
        )
    }
}

@Composable
fun ItemsOthersAndSearch() {
}

@Composable
inline fun <reified T> ItemsAlert(
    title: String,
    message: String,
    listItems: List<T>,
    textPositiveButton: String,
    textColorPositiveButton: Color,
    backgroundColorPositiveButton: Color,
    crossinline onPositiveCallback: () -> Unit,
    crossinline onItemsCallback: (Int, T) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = ColorWhite)
            .padding(ContentInset)
    ) {
        TitleDialog(title = title)
        MessageDialog(message = message)
        LazyColumnCustom(listItems = listItems,
            onItemsCallback = { pos, data -> onItemsCallback(pos, data) })
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = ContentInset),
            contentAlignment = Alignment.CenterEnd
        ) {
            TextClickButton(
                modifier = Modifier.wrapContentWidth(),
                textButton = textPositiveButton,
                textColorButton = textColorPositiveButton,
                backgroundColorButton = backgroundColorPositiveButton
            ) { onPositiveCallback() }
        }
    }
}