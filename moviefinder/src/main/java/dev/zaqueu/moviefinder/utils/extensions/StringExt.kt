package dev.zaqueu.moviefinder.utils.extensions

import android.text.Html
import android.text.Html.FROM_HTML_MODE_COMPACT

fun String.parseHtml(): String {
    return Html.fromHtml(this, FROM_HTML_MODE_COMPACT).toString();
}
