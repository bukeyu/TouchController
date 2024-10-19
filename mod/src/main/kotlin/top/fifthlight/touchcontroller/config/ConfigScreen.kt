package top.fifthlight.touchcontroller.config

import dev.isxander.yacl3.api.OptionDescription
import dev.isxander.yacl3.dsl.YetAnotherConfigLib
import dev.isxander.yacl3.dsl.textSwitch
import net.minecraft.client.gui.screen.Screen
import org.koin.core.context.GlobalContext
import top.fifthlight.touchcontroller.TouchController
import top.fifthlight.touchcontroller.asset.Texts

fun openConfigScreen(parent: Screen): Screen {
    val context = GlobalContext.get()
    val configHolder: TouchControllerConfigHolder = context.get()
    var config = configHolder.config.value
    val layout = configHolder.layout.value

    return YetAnotherConfigLib(TouchController.NAMESPACE) {
        title(Texts.OPTIONS_SCREEN_TITLE)

        val globalCategory by categories.registering("global") {
            name(Texts.OPTIONS_CATEGORY_GLOBAL_TITLE)
            tooltip(Texts.OPTIONS_CATEGORY_GLOBAL_TOOLTIP)

            val disableMouse by rootOptions.registering {
                name(Texts.OPTIONS_CATEGORY_GLOBAL_DISABLE_MOUSE_TITLE)
                description(OptionDescription.of(Texts.OPTIONS_CATEGORY_GLOBAL_DISABLE_MOUSE_DESCRIPTION))
                controller(textSwitch())
                binding(false, { config.disableMouse }, { config = config.copy(disableMouse = it) })
            }

            val disableMouseLock by rootOptions.registering {
                name(Texts.OPTIONS_CATEGORY_GLOBAL_DISABLE_MOUSE_LOCK_TITLE)
                description(OptionDescription.of(Texts.OPTIONS_CATEGORY_GLOBAL_DISABLE_MOUSE_LOCK_DESCRIPTION))
                controller(textSwitch())
                binding(false, { config.disableMouseLock }, { config = config.copy(disableMouseLock = it) })
            }

            val enableTouchEmulation by rootOptions.registering {
                name(Texts.OPTIONS_CATEGORY_GLOBAL_ENABLE_TOUCH_EMULATION_TITLE)
                description(OptionDescription.of(Texts.OPTIONS_CATEGORY_GLOBAL_ENABLE_TOUCH_EMULATION_DESCRIPTION))
                controller(textSwitch())
                binding(false, { config.enableTouchEmulation }, { config = config.copy(enableTouchEmulation = it) })
            }
        }

        categories.register("custom", CustomCategory(
            name = Texts.OPTIONS_CATEGORY_CUSTOM_TITLE,
            tooltip = Texts.OPTIONS_CATEGORY_CUSTOM_TOOLTIP,
            initialConfig = layout
        ))

        save {
            configHolder.saveConfig(config)
            configHolder.saveLayout(layout)
        }
    }.generateScreen(parent)
}