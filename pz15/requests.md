# Запити

```bash
curl http://localhost:8080/api/books

curl http://localhost:8080/api/books/1

curl -i http://localhost:8080/api/books/999

curl -i -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{ "title": "Neuromancer", "author": "William Gibson", "price": 350, "year": 1984, "genre": "Sci-Fi", "coverUrl": "https://example.com/neuromancer.jpg" }'

curl -i -X PUT http://localhost:8080/api/books/1 \
  -H "Content-Type: application/json" \
  -d '{ "title": "Clean Code (2nd ed.)", "author": "Robert Martin", "price": 700, "year": 2008, "genre": "Programming", "coverUrl": "https://example.com/clean-code.jpg" }'

curl -i -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{ "title": "", "author": "William Gibson", "price": -5, "year": 1984 }'

curl -i -X PUT http://localhost:8080/api/books/999 \
  -H "Content-Type: application/json" \
  -d '{ "title": "X", "author": "Y", "price": 1, "year": 2000, "genre": "Z", "coverUrl": "u" }'

curl -i -X DELETE http://localhost:8080/api/books/2

curl -i -X DELETE http://localhost:8080/api/books/999
```
