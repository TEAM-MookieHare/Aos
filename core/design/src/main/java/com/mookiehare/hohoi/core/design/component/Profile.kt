package com.mookiehare.hohoi.core.design.component

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mookiehare.hohoi.core.design.icon.HelloiIcons

@Composable
fun ImageProfile(
    modifier: Modifier = Modifier,
    profileUrl: String? = null,
    size: Int = 48,
    roundedCorner: Int = 40,
    defaultImage: ImageVector = HelloiIcons.Person,
){
    AsyncImage(
        modifier = modifier
            .size(size.dp)
            .clip(RoundedCornerShape(roundedCorner.dp)),
        model = profileUrl,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        error = rememberVectorPainter(image = defaultImage)
    )
}