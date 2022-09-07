package dev.zaqueu.core.data.preferences

import android.content.SharedPreferences
import dev.zaqueu.core.domain.preferences.Preferences

class DefaultPreferences(
    private val sharedPreferences: SharedPreferences
) : Preferences {
    override fun saveShouldShowWelcomeScreen(shouldShow: Boolean) {
        sharedPreferences.edit()
            .putBoolean(Preferences.KEY_SHOULD_SHOW_WELCOME_SCREEN, shouldShow)
            .apply()
    }

    override fun loadShouldShowWelcomeScreen(): Boolean {
        return sharedPreferences.getBoolean(
            Preferences.KEY_SHOULD_SHOW_WELCOME_SCREEN,
            true
        )
    }
}
