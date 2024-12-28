package top.fifthlight.touchcontroller.layout

import top.fifthlight.touchcontroller.assets.Textures
import top.fifthlight.touchcontroller.control.PauseButton

fun Context.PauseButton(config: PauseButton) {
    val (newClick) = Button(id = "pause") {
        if (config.classic) {
            Texture(id = Textures.GUI_PAUSE_PAUSE_CLASSIC)
        } else {
            Texture(id = Textures.GUI_PAUSE_PAUSE)
        }
    }

    result.pause = newClick
}