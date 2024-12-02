package pe.com.master.machines.design.ui.components.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign.Companion.Justify
import androidx.compose.ui.tooling.preview.Preview
import pe.com.master.machines.design.ui.theme.BlueGray500
import pe.com.master.machines.design.ui.theme.ContentInset
import pe.com.master.machines.design.ui.theme.ContentInsetQuarter
import pe.com.master.machines.design.ui.theme.ContentInsetThreeHundred
import pe.com.master.machines.design.ui.theme.DynamicTextSixteen

@Composable
fun MessageDialog(message: String) {
    LazyColumn(
        modifier = Modifier
            .padding(top = ContentInset)
            .fillMaxWidth()
            .heightIn(max = ContentInsetThreeHundred)
    ) {
        item {
            CustomText(
                text = message,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = ContentInsetQuarter),
                fontSize = DynamicTextSixteen,
                textColor = BlueGray500,
                textAlign = Justify,
                maxLines = 50
            )
        }
    }
}

@Preview
@Composable
fun PreviewMessageDialog() {
    MessageDialog("Testñlkjbh ñklkljh ñounh kñjbvlkfnblkmnhv blijhdf ndh{gk bjkñljdfv nñlkbfg mnvñojfdhg poitrgjho´rp djfvñl m cv., m {pvoihjtrpoifv {ñgklmjgo i jh´bpoikjtrfg blmc.vs-,mnbf}e´plk ´pmigj b´pk {lkjn{, ")
}