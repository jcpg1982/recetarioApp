package pe.com.master.machines.design.ui.components.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign.Companion.Start
import androidx.compose.ui.tooling.preview.Preview
import pe.com.master.machines.design.ui.theme.BlueGray700
import pe.com.master.machines.design.ui.theme.ContentInsetQuarter
import pe.com.master.machines.design.ui.theme.DynamicTextEighteen

@Composable
fun TitleDialog(title: String) {
    CustomText(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = ContentInsetQuarter),
        fontSize = DynamicTextEighteen,
        textColor = BlueGray700,
        textAlign = Start,
        maxLines = 1
    )
}

@Preview
@Composable
fun PreviewTitleDialog() {
    TitleDialog("Testñlkjbh ñklkljh ñounh kñjbvlkfnblkmnhv blijhdf ndh{gk bjkñljdfv nñlkbfg mnvñojfdhg poitrgjho´rp djfvñl m cv., m {pvoihjtrpoifv {ñgklmjgo i jh´bpoikjtrfg blmc.vs-,mnbf}e´plk ´pmigj b´pk {lkjn{, ")
}