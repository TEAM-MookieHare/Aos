package com.mookiehare.hohoi.core.design.component

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
    selected : Boolean,
    onSelectedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled : Boolean = true,
    label : @Composable () -> Unit
){
    FilterChip(
        selected = selected,
        onClick = { onSelectedChange(!selected) },
        label = {
            ProvideTextStyle(value = MaterialTheme.typography.bodySmall) {
                label()
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
            disabledContainerColor = if (selected){
                MaterialTheme.colorScheme.onBackground.copy( alpha = HelloiChipDefault.DisabledChipContainerAlpha )
            }else{
                Color.Transparent
            },
            disabledLabelColor = MaterialTheme.colorScheme.onBackground.copy( alpha = HelloiChipDefault.DisabledChipContentAlpha )
        )
    )
}

object HelloiChipDefault{
    const val DisabledChipContainerAlpha = 0.12f
    const val DisabledChipContentAlpha = 0.38f
    val ChipBorderWidth = 1.dp
}
