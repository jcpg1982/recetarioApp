package pe.com.master.machines.design.ui.components.custom

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import pe.com.master.machines.design.ui.theme.ColorWhite

@Composable
fun NoteActions(
    modifier: Modifier = Modifier,
    isSaved: Boolean,
    iconSaved: ImageVector,
    onClickSavedNote: () -> Unit,
    onClickUnsavedNote: () -> Unit,
    onClickSharedNote: () -> Unit = {}
) {
    var currentSavedState by remember { mutableStateOf(isSaved) }

    Row(
        modifier = modifier
    ) {
        Icon(
            modifier = Modifier.clickable {
                if (currentSavedState) onClickUnsavedNote() else onClickSavedNote()
                currentSavedState = !currentSavedState
            },
            imageVector = iconSaved,
            contentDescription = "saved",
            tint = ColorWhite
        )
        /*Icon(
            modifier = Modifier.clickable { onClickSharedNote() },
            imageVector = Icons.Filled.Share,
            contentDescription = "shared",
            tint = ColorWhite
        )*/
    }
}

@Preview
@Composable
fun PreviewNoteActions() {
    NoteActions(
        isSaved = true,
        iconSaved = Icons.Filled.BookmarkBorder,
        onClickSavedNote = {},
        onClickUnsavedNote = {},
        onClickSharedNote = {}
    )
}
