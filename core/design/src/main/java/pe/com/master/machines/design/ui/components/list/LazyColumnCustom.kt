package pe.com.master.machines.design.ui.components.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pe.com.master.machines.design.ui.components.rows.RowItemDialog
import pe.com.master.machines.design.ui.theme.BlueGray600
import pe.com.master.machines.design.ui.theme.ContentInset
import pe.com.master.machines.design.ui.theme.ContentInsetFive
import pe.com.master.machines.design.ui.theme.ContentInsetThreeHundred
import pe.com.master.machines.design.ui.theme.ContentInsetTwo


@Composable
inline fun <reified T> LazyColumnCustom(
    listItems: List<T>, crossinline onItemsCallback: (Int, T) -> Unit
) {
    val rounded = RoundedCornerShape(ContentInsetFive)
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = ContentInset)
            .heightIn(max = ContentInsetThreeHundred),
        verticalArrangement = Arrangement.spacedBy(ContentInsetTwo)
    ) {
        listItems.forEachIndexed { index, data ->
            item {
                when (data) {
                    is String -> {
                        RowItemDialog(text = data, color = BlueGray600) {
                            onItemsCallback(index, data)
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewLazyColumnCustom() {
    LazyColumnCustom(listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")) { pos, data -> }
}