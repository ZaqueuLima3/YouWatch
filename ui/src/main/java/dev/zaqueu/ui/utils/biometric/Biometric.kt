package dev.zaqueu.ui.utils.biometric

import androidx.biometric.BiometricPrompt
import androidx.fragment.app.FragmentActivity

sealed class BiometricEvent {
    data class OnShowBiometric(
        val onSuccess: () -> Unit = {},
        val onError: () -> Unit = {},
        val onFailed: () -> Unit = {},
    ) : BiometricEvent() {
        fun showBiometricPrompt(activity: FragmentActivity) {
            val promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle("title")
                .setSubtitle("Subtitle")
                .setNegativeButtonText("Cancel")
                .build()

            val biometricPrompt = BiometricPrompt(
                activity,
                object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                        onError()
                    }

                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                        onSuccess()
                    }

                    override fun onAuthenticationFailed() {
                        onFailed()
                    }
                }
            )

            biometricPrompt.authenticate(promptInfo)
        }
    }

    fun show(activity: FragmentActivity) {
        when (this) {
            is OnShowBiometric -> this.showBiometricPrompt(activity)
        }
    }
}
