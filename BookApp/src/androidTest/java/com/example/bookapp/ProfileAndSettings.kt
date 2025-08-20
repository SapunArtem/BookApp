package com.example.bookapp

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import com.example.bookapp.presentation.BookApp
import org.junit.Rule
import org.junit.Test

/**
 * UI-тесты для профиля пользователя и экрана настроек.
 *
 * Проверяется:
 * - редактирование профиля пользователя;
 * - смена темы оформления;
 * - смена языка интерфейса.
 */
class ProfileAndSettings {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<BookApp>()

    /**
     * Тестирует процесс редактирования профиля.
     *
     * Шаги:
     * 1. Перейти в раздел профиля.
     * 2. Проверить, что отображаются имя и email.
     * 3. Войти в режим редактирования.
     * 4. Очистить поля и ввести новые данные.
     * 5. Сохранить изменения.
     * 6. Проверить, что новые данные отображаются в профиле.
     */
    @Test
    fun editProfileInformation() {

        // Переходим в профиль
        composeTestRule.onNodeWithTag(R.string.profile.toString()).performClick()

        composeTestRule.waitUntil(5_000) {
            composeTestRule.onAllNodesWithTag("Name").fetchSemanticsNodes().isNotEmpty()
        }
        // Проверяем начальные данные
        composeTestRule.onNodeWithTag("Name").assertIsDisplayed()
        composeTestRule.onNodeWithTag("Email").assertIsDisplayed()

        // Открываем редактирование
        composeTestRule.onNodeWithTag("BtnEditProfile").performClick()

        // Редактируем данные
        composeTestRule.onNodeWithTag("InputFieldName").performTextClearance()
        composeTestRule.onNodeWithTag("InputFieldName").performTextInput("Test User")
        composeTestRule.onNodeWithTag("InputFieldEmail").performTextClearance()
        composeTestRule.onNodeWithTag("InputFieldEmail").performTextInput("test@example.com")

        // Сохраняем
        composeTestRule.onNodeWithTag("BtnSave").performClick()

        // Проверяем изменения
        composeTestRule.onNodeWithText("Test User").assertIsDisplayed()
        composeTestRule.onNodeWithText("test@example.com").assertIsDisplayed()
    }

    /**
     * Тестирует изменение темы и языка интерфейса.
     *
     * Шаги:
     * 1. Перейти в настройки.
     * 2. Проверить отображение секций для языка и темы.
     * 3. Переключить тему (светлая ↔ тёмная).
     * 4. Переключить язык (например, на русский).
     * 5. Вернуться на главный экран и убедиться, что язык изменился.
     */
    @Test
    fun changeThemeAndLanguage() {

        // Открываем настройки
        composeTestRule.onNodeWithTag("settingsButton").performClick()

        // Проверяем настройки
        composeTestRule.onNodeWithTag("LanguageSettingSection").assertIsDisplayed()
        composeTestRule.onNodeWithTag("ThemeSettingsSection").assertIsDisplayed()

        // Меняем тему на темную
        // Проверка темы: кликаем по первому чекбоксу (светлая тема)
        val themeOptions = composeTestRule.onAllNodesWithTag("CheckBoxOption")
        themeOptions[0].performClick()
        // Переключаем обратно на тёмную
        themeOptions[1].performClick()

        val languageOptions = composeTestRule.onAllNodesWithTag("LanguageOption")
        languageOptions[0].performClick()
        // Переключаем обратно на тёмную
        languageOptions[1].performClick()


        // Возвращаемся назад
        composeTestRule.onNodeWithTag("backBtn").performClick()

        // Проверяем изменение языка в интерфейсе
        composeTestRule.onNodeWithText("Избранные").assertExists()
        composeTestRule.onNodeWithText("Профиль").assertExists()

    }
}
