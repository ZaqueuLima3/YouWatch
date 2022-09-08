package dev.zaqueu.onboarding.domain.usecases

class FilterDigits {
    operator fun invoke(text: String): String {
        return text.filter { it.isDigit() }
    }
}
