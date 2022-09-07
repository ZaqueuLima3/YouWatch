package dev.zaqueu.core.data.preferences

import android.content.SharedPreferences
import dev.zaqueu.core.domain.preferences.Preferences

class DefaultPreferences(
    private val sharedPreferences: SharedPreferences
) : Preferences {

}
