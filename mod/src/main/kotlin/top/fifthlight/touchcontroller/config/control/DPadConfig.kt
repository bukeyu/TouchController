package top.fifthlight.touchcontroller.config.control

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.plus
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import net.minecraft.text.Text
import top.fifthlight.touchcontroller.asset.Texts
import top.fifthlight.touchcontroller.layout.Align
import top.fifthlight.touchcontroller.layout.Context
import top.fifthlight.touchcontroller.layout.DPad
import top.fifthlight.touchcontroller.proxy.data.IntOffset
import top.fifthlight.touchcontroller.proxy.data.IntSize
import kotlin.math.round

@Serializable
data class DPadConfig(
    var classic: Boolean = true,
    var size: Float = 1f,
    var padding: Int = 4,
    override val align: Align = Align.LEFT_BOTTOM,
    override val offset: IntOffset = IntOffset.ZERO,
    override val opacity: Float = 1f
) : ControllerWidgetConfig() {
    @Suppress("UNCHECKED_CAST")
    @Transient
    override val properties = super.properties + persistentListOf<Property<DPadConfig, *>>(
        FloatProperty(
            getValue = { it.size },
            setValue = { config, value -> config.copy(size = value) },
            startValue = .5f,
            endValue = 2f,
            messageFormatter = {
                Text.translatable(
                    Texts.OPTIONS_WIDGET_DPAD_PROPERTY_SIZE,
                    round(opacity * 100f).toString()
                )
            },
        ),
        BooleanProperty(
            getValue = { it.classic },
            setValue = { config, value -> config.copy(classic = value) },
            message = Texts.OPTIONS_WIDGET_DPAD_PROPERTY_CLASSIC,
        )
    ) as PersistentList<Property<ControllerWidgetConfig, *>>

    fun buttonSize(): IntSize = IntSize((22f * size).toInt() * 3, (22f * size).toInt() * 3)

    override fun size(): IntSize = buttonSize() * 3 + padding * 2

    override fun render(context: Context) = context.DPad(this@DPadConfig)

    override fun cloneBase(align: Align, offset: IntOffset, opacity: Float) = copy(
        align = align,
        offset = offset,
        opacity = opacity
    )
}
