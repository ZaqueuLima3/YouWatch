package dev.zaqueu.ui.utils.biometric

import androidx.biometric.BiometricPrompt
import androidx.fragment.app.FragmentActivity
import dev.zaqueu.ui.utils.text.TextResource

class Biometric(
    private val title: TextResource,
    private val subtitle: TextResource,
    private val negativeButton: TextResource,
    private val onSuccess: () -> Unit = {},
    private val onError: () -> Unit = {},
    private val onFailed: () -> Unit = {},
) {
    fun show(activity: FragmentActivity) {
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(title.toString(activity))
            .setSubtitle(subtitle.toString(activity))
            .setNegativeButtonText(negativeButton.toString(activity))
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
