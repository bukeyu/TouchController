package top.fifthlight.combine.layout

import androidx.compose.runtime.Stable
import top.fifthlight.combine.modifier.Constraints
import top.fifthlight.data.IntOffset
import top.fifthlight.data.IntSize
import top.fifthlight.data.Offset

data class MeasureResult(
    val width: Int,
    val height: Int,
    val placer: Placer,
)

@Stable
fun interface MeasurePolicy {
    fun MeasureScope.measure(measurables: List<Measurable>, constraints: Constraints): MeasureResult

    fun measure(measurables: List<Measurable>, constraints: Constraints) =
        with(MeasureScope) { this.measure(measurables, constraints) }
}

object MeasureScope {
    fun layout(
        width: Int,
        height: Int,
        placer: Placer
    ) = MeasureResult(
        width = width, height = height, placer = placer
    )

    fun layout(size: IntSize, placer: Placer) = layout(size.width, size.height, placer)
}

@Stable
fun interface Placer {
    fun placeChildren()
}

interface Measurable {
    val parentData: Any?
    fun measure(constraints: Constraints): Placeable
}

interface Placeable {
    val x: Int
    val y: Int
    val absoluteX: Int
    val absoluteY: Int
    val width: Int
    val height: Int

    fun placeAt(x: Int, y: Int)
    fun placeAt(offset: IntOffset) = placeAt(offset.x, offset.y)

    val position get() = IntOffset(x, y)
    val absolutePosition get() = IntOffset(absoluteX, absoluteY)
    val size get() = IntSize(width, height)
}

operator fun Placeable.contains(offset: Offset): Boolean {
    val xInRange = absoluteX <= offset.x && offset.x < absoluteX + width
    val yInRange = absoluteY <= offset.y && offset.y < absoluteY + height
    return xInRange && yInRange
}
