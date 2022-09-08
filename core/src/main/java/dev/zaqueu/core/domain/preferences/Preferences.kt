package dev.zaqueu.core.domain.preferences

import dev.zaqueu.core.domain.modules.User

interface Preferences {
    fun saveShouldShowWelcomeScreen(shouldShow: Boolean)
    fun loadShouldShowWelcomeScreen(): Boolean
    fun saveUserData(user: User)
    fun loadUserData(): User

    companion object {
        const val KEY_SHOULD_SHOW_WELCOME_SCREEN = "welcome_screen"
        const val KEY_USERNAME = "username"
        const val KEY_USER_PIN = "pin"
    }
}
