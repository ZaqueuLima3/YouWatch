package dev.zaqueu.ui.utils.text

import android.content.Context

sealed class TextResource {
    data class DynamicText(val text: String): TextResource()
    data class StringResource(val resId: Int): TextResource()

    fun toString(context: Context): String {
        return when(this) {
            is DynamicText -> text
            is StringResource -> context.getString(resId)
        }
    }
}
