package top.fifthlight.touchcontroller.layout

import top.fifthlight.touchcontroller.assets.Textures
import top.fifthlight.touchcontroller.control.DescendButton
import top.fifthlight.touchcontroller.control.DescendButtonTexture

fun Context.DescendButton(config: DescendButton) {
    val (_, clicked) = SwipeButton(id = "descend") { clicked ->
        when (Pair(config.texture, clicked)) {
            Pair(DescendButtonTexture.CLASSIC, false) -> Texture(id = Textures.GUI_DESCEND_DESCEND_CLASSIC)
            Pair(DescendButtonTexture.CLASSIC, true) -> Texture(
                id = Textures.GUI_DESCEND_DESCEND_CLASSIC,
                color = 0xFFAAAAAAu
            )

            Pair(DescendButtonTexture.SWIMMING, false) -> Texture(id = Textures.GUI_DESCEND_WATERDESCEND)
            Pair(DescendButtonTexture.SWIMMING, true) -> Texture(id = Textures.GUI_DESCEND_WATERDESCEND_ACTIVE)
            Pair(DescendButtonTexture.FLYING, false) -> Texture(id = Textures.GUI_DESCEND_FLYINGDESCEND)
            Pair(DescendButtonTexture.FLYING, true) -> Texture(id = Textures.GUI_DESCEND_FLYINGDESCEND_ACTIVE)
        }
    }
    result.sneak = result.sneak || clicked
}