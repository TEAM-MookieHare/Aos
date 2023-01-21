package com.mookiehare.hohoi.core.design.extensions

import android.graphics.drawable.Drawable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.nativeCanvas
import androidx.core.graphics.drawable.updateBounds

/**
 * 9 패치 이미지를 background image로 load를 하지 못해서 drawBehind를 이용하여 작성
 * 9 패치 드로우 extensions 함수
 */
fun Modifier.drawNinePatch(drawable: Drawable?) = this.drawBehind {
    drawable?.updateBounds(0, 0, size.width.toInt(), size.height.toInt())
    drawable?.draw(drawContext.canvas.nativeCanvas)
}