package dev.zaqueu.core.domain.preferences

interface Preferences {
    fun saveShouldShowWelcomeScreen(shouldShow: Boolean)

    fun loadShouldShowWelcomeScreen(): Boolean

    companion object {
        const val KEY_SHOULD_SHOW_WELCOME_SCREEN = "welcome_screen"
    }
}
