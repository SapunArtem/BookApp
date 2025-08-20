package com.example.bookapp


import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import com.example.bookapp.presentation.BookApp
import com.example.bookapp.presentation.components.bottomBar.BottomList
import org.junit.Rule
import org.junit.Test

class SearchAndNavigation{
    @get:Rule
    val composeTestRule = createAndroidComposeRule<BookApp>()

    @Test
    fun searchBooksAndNavigateToDetails() {

        // Ждем загрузки
        composeTestRule.waitUntil(5000) {
            composeTestRule.onAllNodesWithTag("BookList").fetchSemanticsNodes().isNotEmpty()
        }
        // Проверяем, что отображается домашний экран
        composeTestRule.onNodeWithTag("BookList").assertIsDisplayed()
        composeTestRule.onAllNodesWithTag("BookCard")[0].assertIsDisplayed()

        // Ждем загрузки
        composeTestRule.waitUntil(5000) {
            composeTestRule.onAllNodesWithTag("BookSearch").fetchSemanticsNodes().isNotEmpty()
        }
        // Вводим поисковый запрос
        composeTestRule.onNodeWithTag("BookSearch").assertIsDisplayed()
        composeTestRule.onNodeWithTag("BooksSearchInputField").performTextInput("android")
        composeTestRule.onNodeWithTag("BooksSearchInputField").performImeAction()


        // Ждем загрузки
        composeTestRule.waitUntil(5000) {
            composeTestRule.onAllNodesWithTag("BookList").fetchSemanticsNodes().isNotEmpty()
        }

        // Проверяем результаты поиска
        composeTestRule.onNodeWithText("android", substring = true, ignoreCase = true)
            .assertExists()
            .assertIsDisplayed()

        // Переходим к деталям книги
        composeTestRule.onAllNodes(hasTestTag("BookCard"))[0].performClick()

        // Ожидание загрузки деталей
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodes(hasTestTag("BookTitle")).fetchSemanticsNodes().isNotEmpty()
        }

        // Проверяем экран деталей
        composeTestRule.onNodeWithTag("BookTitle").assertIsDisplayed()
        composeTestRule.onNodeWithTag("BookDescription").assertExists()

        // Возврат на главный экран
        composeTestRule.onNodeWithTag("backBtn").performClick()

        // Проверка отображения списка фильмов
        composeTestRule.onNodeWithTag("BookList").assertIsDisplayed()
    }


    @Test
    fun bottomBar_navigationItems_areDisplayedAndClickable() {
        BottomList.bottomItemsList.forEach { item ->
            composeTestRule
                .onNodeWithTag(item.title.toString())
                .assertIsDisplayed() // Проверка видимости
                .performClick() // Проверка кликабельности
        }

    }
}