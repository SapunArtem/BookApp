package com.example.bookapp.domain.useCases.Settings

import com.example.bookapp.domain.repository.LocalizationRepository
import javax.inject.Inject

class ChangeAppLanguageUseCase @Inject constructor(
    private val localizationRepository: LocalizationRepository
) {

    operator fun invoke(languageCode: String) {
        localizationRepository.setLanguage(languageCode)
    }

}