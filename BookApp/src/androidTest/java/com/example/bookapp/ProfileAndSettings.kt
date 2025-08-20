package com.example.bookapp

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isDisplayed
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

class ProfileAndSettings {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<BookApp>()

    @Test
    fun editProfileInformation() {

        // Переходим в профиль
        composeTestRule.onNodeWithTag(R.string.profile.toString()).performClick()

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
