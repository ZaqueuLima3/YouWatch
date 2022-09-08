package dev.zaqueu.core.data.preferences

import android.content.SharedPreferences
import dev.zaqueu.core.domain.modules.User
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

    override fun saveUserData(user: User) {
        sharedPreferences.edit()
            .putString(Preferences.KEY_USER_PIN, user.pin)
            .putString(Preferences.KEY_USERNAME, user.username)
            .apply()
    }

    override fun loadUserData(): User {
        val username = sharedPreferences.getString(Preferences.KEY_USERNAME, "").orEmpty()
        val pin = sharedPreferences.getString(Preferences.KEY_USER_PIN, "").orEmpty()

        return User(
            username = username,
            pin = pin
        )
    }
}
