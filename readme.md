A Spring Boot application which exposes these REST endpoints:

Technology - Java 8,Maven,Swagger

SWAGGER UI : http://localhost:8080/swagger-ui.html

Service
•	GET /books - http://localhost:8080/books
Returns a list of books. The books have an ISBN number, title and author.

Output: [ {  "isbnNumber" : "1000", "title" : "Book1",   "author" : "Author1",  "summary" : "Summary1"}, {  "isbnNumber" : "1001",  "title" : "Think And Grow Rich",  "author" : "Napoleon Hill",  "summary" : "Napoleon Hill's classic Think and Grow Rich, has made more millionaires and inspired more successes than any other book in history. "}, {  "isbnNumber" : "1002",  "title" : "Wings of Fire: An Autobiography of Abdul Kalam",  "author" : "Arun Tiwari",  "summary" : "he 'Wings of Fire' is one such autobiography by visionary scientist Dr. APJ Abdul Kalam, who from very humble beginnings rose to be the President of India."}]

•	GET /books/<isbn> - http://localhost:8080/books/1001
Returns the book with the given ISBN number. An additional field on the book object “summary” is also returned.

Output: { "isbnNumber": "1001", "title": "Think And Grow Rich", "author": "Napoleon Hill", "summary": "Napoleon Hill's classic Think and Grow Rich, has made more millionaires and inspired more successes than any other book in history. " }

•	GET /search<query> - http://localhost:8080/books/?author=Nap&title=law
Returns the books which match the query. You should be able to search for partial titles and authors. I.e. a search for “lord” finds “lord of the rings”.

Output: [ {  "isbnNumber" : "1007",  "title" : "Law Of Success",  "author" : "Napoleon Hill",  "summary" : "A true masterpiece with the fundamentals of the Law of Success philosophy."}, {  "isbnNumber" : "1008",  "title" : "Law Of Success Edition2",  "author" : "Napoleon Hill",  "summary" : "A true masterpiece with the fundamentals of the Law of Success philosophy."}, {  "isbnNumber" : "1009",  "title" : "Law Of Success Edition3",  "author" : "Napoleon Hill",  "summary" : "A true masterpiece with the fundamentals of the Law of Success philosophy."} ]

•	POST /order	- http://localhost:8080/order

Input: {"userId": "11","bookOrderedDto": {"bookDtos": [{"isbnNumber": "12341","title": "Book5","author": "Author5","quantity": 7,"price": 100,"summary": "This is the summary"}]}}

This endpoint should allow the user to order multiple books. Use some form of validation to verify the payloads content. Make an async call to https://jsonplaceholder.typicode.com/posts to simulate a call to a backend service.

Output: "0": { "userId": "1", "id" : "1234", "title": "Book5", "author": "Author5", } }
Note: It not showing the post that I submit. But showing the posts that are available