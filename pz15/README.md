# ПЗ 1.5: тести й обробка помилок для Book

Запуск тестів (Java 21):

```bash
./mvnw test
```

## Покрито

| Сценарій | Unit (`BookServiceImplTest`) | Integration (`BookControllerTest`) |
|---|---|---|
| findAll → список | так | 200, `$[0].title` |
| findById існуючий | так | 200, деталі |
| findById неіснуючий | виняток `BookNotFoundException` | 404, `$.status`, `$.message` |
| create | так, повертає `id` | 201 |
| create невалідний | - | 400, `$.details` масив |
| update | - | 200 / 404 |
| delete | - | 204 / 404 |

## Не покрито

- `update` і `delete` на рівні unit: їх поведінка (404 для неіснуючого) перевірена через контролер, матриця вимагає для них лише integration.
- `InMemoryBookRepository`, `BookMapper`, `BookSeeder` окремо не тестуються: логіка тривіальна, а в unit-тесті сервісу вони замінені моками.
- Некоректний JSON і нечисловий `id` у шляху (`/api/books/abc`): Spring повертає власну 400, обробник для них не вимагався.
- Повний контекст (`@SpringBootTest`) не піднімається: завдання вимагає лише `@WebMvcTest`.
