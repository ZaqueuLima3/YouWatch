package dev.zaqueu.onboarding.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dev.zaqueu.onboarding.domain.usecases.FilterDigits

@Module
@InstallIn(ViewModelComponent::class)
object OnboardingDomainModule {
    @Provides
    @ViewModelScoped
    fun provideFilterDigits(): FilterDigits {
        return FilterDigits()
    }
}
