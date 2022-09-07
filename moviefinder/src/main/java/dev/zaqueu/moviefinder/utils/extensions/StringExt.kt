package dev.zaqueu.moviefinder.utils.extensions

import android.text.Html
import android.text.Html.FROM_HTML_MODE_COMPACT
import java.util.Locale

fun String.parseHtml(): String {
    return Html.fromHtml(this, FROM_HTML_MODE_COMPACT).toString();
}

fun String.capitalized(): String {
    return this.lowercase().replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.ROOT
        ) else it.toString()
    }
}
