package top.fifthlight.combine.widget.base

import androidx.compose.runtime.Composable
import top.fifthlight.combine.data.Texture
import top.fifthlight.combine.layout.Layout
import top.fifthlight.combine.modifier.Modifier
import top.fifthlight.data.IntSize
import top.fifthlight.data.Offset
import top.fifthlight.data.Rect

@Composable
fun Texture(
    texture: Texture,
    uvRect: Rect = Rect.ONE,
    size: IntSize,
    modifier: Modifier = Modifier,
) {
    Layout(
        modifier = modifier,
        measurePolicy = { _, constraints ->
            layout(
                width = size.width.coerceIn(constraints.minWidth, constraints.maxWidth),
                height = size.height.coerceIn(constraints.minHeight, constraints.maxHeight)
            ) {}
        },
        renderer = {
            canvas.drawTexture(
                texture = texture,
                dstRect = Rect(offset = Offset.ZERO, size = size.toSize()),
                uvRect = uvRect
            )
        }
    )
}