package top.fifthlight.combine.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable

@Immutable
interface ItemStack {
    val amount: Int
    val id: Identifier

    fun withAmount(amount: Int): ItemStack

    companion object {
        @Composable
        fun of(id: Identifier, amount: Int = 1) = LocalItemFactory.current.createItemStack(id, amount)
    }
}