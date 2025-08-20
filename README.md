[![Android CI](https://github.com/SapunArtem/BookApp/actions/workflows/CI.yml/badge.svg)](https://github.com/SapunArtem/BookApp/actions/workflows/CI.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

# 📚Book App
---
Android приложение для поиска, просмотра и управления книгами с использованием Google Books API. Разработано с использованием современных Android технологий и лучших практик.

---
## ✨ Возможности

- 🔍 **Поиск книг** - Поиск по Google Books API
- 🔍 **Детали** - Просмотр деталей о книге
- ⭐ **Избранное** - Сохранение любимых книг для оффлайн доступа
- 🎨 **Темы** - Поддержка светлой и темной темы
- 🌍 **Локализация** - Поддержка английского и русского языков
- 👤 **Профиль** - Персонализация пользовательского профиля
- 📱 **UI** - Современный интерфейс на Jetpack Compose
- 📱 **UI** - Возможность смены темы приложения
- 🚀 **Производительность** - Оптимизированная архитектура

---
## 🛠 Технологический стек

### Архитектура
- **Clean Architecture** - Многослойная архитектура
- **MVVM** - Model-View-ViewModel паттерн

### Jetpack Components
- **Compose** - Современный declarative UI
- **Navigation** - Навигация между экранами
- **Room** - Локальная база данных
- **Hilt** - Dependency injection
- **ViewModel** - Управление состоянием UI
- **Flow** - Асинхронные потоки данных

### Сеть
- **Retrofit** - HTTP клиент
- **Gson-converter** - JSON парсинг
- **OkHttp** - Сетевой стек с логированием

### Другие библиотеки
- **Glide** - Загрузка изображений
- **MockK** - Моки для тестирования

---

## 📦 Установка

### Требования
- Android Studio 2022.3.1+
- Android SDK 33+
- Java 17+

### Шаги установки

### 1. **Клонируйте репозиторий**
   
   ```git clone https://github.com/SapunArtem/BookApp.git```

### 2. **Для Google books Api:**
   
  - Получите API ключ Google Books

  - Перейдите на Google Cloud Console

  - Создайте новый проект

  - Включите Google Books API

  - Создайте API ключ

### 3.**В проекте:**
**Настройте переменные окружения**

```
Создайте файл local.properties в корне проекта:

properties
BOOKS_API_KEY=your_google_books_api_key_here
```

- Запустите приложение

- Откройте проект в Android Studio

- Дождитесь завершения сборки

- Запустите на эмуляторе или устройстве


---
## 🏗 Структура проекта

```
BookApp/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/bookapp/
│   │   │   │   ├── data/
│   │   │   │   │   ├── local/          # Room database, DAOs, Entities
│   │   │   │   │   ├── remote/         # Retrofit, API services, DTOs
│   │   │   │   │   └── repository/     # Repository implementations
│   │   │   │   ├── di/                 # Hilt modules, dependency injection
│   │   │   │   ├── domain/             # Use cases, repository interfaces
│   │   │   │   ├── presentation/       # ViewModels, Composables, Screens
│   │   │   │   └── utils/              # Extensions, constants, helpers
│   │   ├── androidTest/                # Instrumented tests
│   │   └── test/                       # Unit tests
│   └── build.gradle
├── build.gradle
└── settings.gradle
```
---
## 🎨 Скриншоты

<table>
  <tr>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/65fbf991-e241-4b7d-b1ed-25fdcca6a6d9" width="300"/><br>
      Favorite
    </td>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/e9a157bb-8821-43d0-95b6-8a9ec72a554a" width="300"/><br>
      Profile
    </td>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/96ad89c7-4ee0-486a-a39a-be31661b38bd" width="300"/><br>
      Edit Profile
    </td>
  </tr>
  <tr>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/ba6c414c-debf-4594-ab38-8e81f2d5abea" width="300"/><br>
      Settings
    </td>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/b99a4879-bb89-4b58-b149-90e1efba394e" width="300"/><br>
      Main
    </td>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/c1a8bbb2-c6f5-4f88-83fc-380936cb0a04" width="300"/><br>
      Details
    </td>
  </tr>
</table>

---

## 🚀 Использование
#### 1.Поиск книг

- Откройте вкладку "Home"

- Введите поисковый запрос в строке поиска

- Просмотрите результаты поиска

- Нажмите на книгу для просмотра деталей

#### 2.Управление избранным

- На экране деталей книги нажмите на иконку сердца

- Перейдите во вкладку "Favorites" для просмотра сохраненных книг

- Для удаления из избранного нажмите на иконку сердца еще раз

#### 3.Настройки

- Нажмите на иконку настроек в правом верхнем углу

- Выберите предпочтительную тему и язык

#### 4.Редактирование профиля

- Перейдите во вкладку "Profile"

- Нажмите кнопку "Edit Profile"

- Измените имя и email

- Сохраните изменения

---

## 🧪 Тестирование
**В проекте используется :**

- **Unit tests** - покрывающие все UseCases, ViewModel,Repository
- **UI End-To-End tests** - Покрывающие основные пользовательские сценарии


---

## 🔧 Настройка CI/CD
Проект использует GitHub Actions для автоматизации:

Android CI - Сборка и тестирование при каждом commit

---

## 🐛 Известные проблемы

- Иногда медленная загрузка изображений при плохом соединении

- Ограничение API запросов Google Books

- Нет поддержки оффлайн режима для поиска

---
## 🔮 Планы на будущее

- Оффлайн режим поиска

- Кеширование изображений

- Push уведомления для новых книг

- Social features (рецензии, рейтинги)

- Интеграция с другими book APIs

---
## 🤝 Сотрудничество

**Мы приветствуем сотрудничество! Пожалуйста, следуйте этим шагам:**

- Fork репозитория

- Создайте feature branch (git checkout -b feature/amazing-feature)

- Commit изменений (git commit -m 'Add amazing feature')

- Push в branch (git push origin feature/amazing-feature)

- Откройте Pull Request

**Рекомендации**

- Следуйте существующему стилю кода

- Добавляйте тесты для новой функциональности

- Обновляйте документацию при необходимости


---

## 📝 Лицензия

MIT License

Copyright (c) 2025 Sapun Artem

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

---
## 📞 Поддержка
Если у вас есть вопросы или предложения:

📧 Email: sapunartemNikokaevich@gmail.com

---
<div align="left">
⭐ Если вам нравится проект, поставьте звезду на GitHub! ⭐

</div> ```
