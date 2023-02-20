package com.mookiehare.hohoi.core.design.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
/**
 * Helloi filter chip.
 *
 * @param selected Whether the chip is currently checked.
 * @param onSelectedChange Called when the user clicks the chip and toggles checked.
 * @param modifier Modifier to be applied to the chip.
 * @param enabled Controls the enabled state of the chip. When `false`, this chip will not be
 * clickable and will appear disabled to accessibility services.
 * @param label The text label content.
 */

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HelloiChip(
    elements : List<Chip>,
    modifier: Modifier = Modifier,
    enabled : Boolean = true
){
    Surface(
        color = Color.Transparent
    ) {
        LazyRow(
            modifier = Modifier.padding(start = 10.dp, end = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            items(elements.size){ index ->
                FilterChip(
                    selected = elements[index].isSelected.value,
                    onClick = { elements[index].isSelected.value = !elements[index].isSelected.value },
                    label = {
                        ProvideTextStyle(value = MaterialTheme.typography.bodySmall) {
                            Text(text = elements[index].text)
                        }
                    },
                    modifier = modifier,
                    enabled = enabled,
                    shape = CircleShape,
                    border = FilterChipDefaults.filterChipBorder(
                        borderColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        selectedBorderWidth = HelloiChipDefault.ChipBorderWidth,
                        disabledBorderColor = MaterialTheme.colorScheme.onBackground.copy(
                            alpha = HelloiChipDefault.DisabledChipContentAlpha
                        ),
                        disabledSelectedBorderColor = MaterialTheme.colorScheme.onBackground.copy(
                            alpha = HelloiChipDefault.DisabledChipContentAlpha
                        )
                    ),
                    colors = FilterChipDefaults.filterChipColors(
                        labelColor = MaterialTheme.colorScheme.onBackground,
                        selectedContainerColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        selectedLabelColor = Color.White,
                        disabledContainerColor = if (elements[index].isSelected.value){
                            MaterialTheme.colorScheme.onBackground.copy( alpha = HelloiChipDefault.DisabledChipContainerAlpha )
                        }else{
                            Color.Transparent
                        },
                        disabledLabelColor = MaterialTheme.colorScheme.onBackground.copy( alpha = HelloiChipDefault.DisabledChipContentAlpha )
                    )
                )
            }
        }
    }
}

private object HelloiChipDefault{
    const val DisabledChipContainerAlpha = 0.12f
    const val DisabledChipContentAlpha = 0.38f
    val ChipBorderWidth = 1.dp
}

data class Chip (
    val text : String = "",
    var isSelected : MutableState<Boolean>
)