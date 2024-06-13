# API веб приложение для управления сущностями Book и Category

Это API веб приложение разработано для управления книгами (Books) и их категориями (Categories). Пользователи могут выполнять различные операции с книгами и категориями, такие как создание, чтение, обновление и удаление.

## Требования

Для запуска данного API веб приложения вам потребуется:
- docker (образы Redis и PostgreSQL)

## Установка

1. Клонируйте репозиторий:

   
   git clone https://github.com/your-repo/api-books-categories.git
   

2. Установите зависимости:

   
   выполните команду docker compose UP
   


## API Эндпоинты

### Книги (Books):

- GET /api/book - Получить все книги
- POST /api/book - Создать новую книгу
- GET /api/book/:id - Получить информацию о книге по ID
- PUT /api/book/:id - Обновить информацию о книге по ID
- DELETE /api/book/:id - Удалить книгу по ID
- GET /api/book/filter/category (параметр - categoryName) - получить книги по названию категории
- GET /api/book/filter/filter (параметры - bookName, author) - получить книгу по названию и автору

