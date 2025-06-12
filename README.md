# BookReviewManager

## 📚 Description

BookReviewManager is a Java console application built with Maven for managing a library of books and their reviews.  
It leverages the **Java Stream API** to efficiently filter, sort, group, and compute statistics on book and review data.

---

## ⚡ Main Features

### 1. Display Books Sorted by Average Rating
- Lists all books, ordered from highest to lowest average review score.
- Uses Java Streams for mapping and sorting.

### 2. Show Reviews for a Given Author
- Enter an author’s name:  
  The program finds all books by that author and displays all their reviews, sorted by newest first.

### 3. Top Books by Genre
- For each genre, displays the **top 3 books** based on average rating.

### 4. Filter Books Published Before a Certain Year
- Enter a year:  
  The program lists all books published before that year, along with their reviews.

### 5. Genre Statistics
- For each genre:  
  Shows the number of books and the average rating (the mean of the averages).

---

## 🛠️ Project Structure

- **`model/Book.java`**: Represents a book (title, author, publication year, genre, etc.)
- **`model/Review.java`**: Represents a review (rating, comment, date, etc.)
- **`model/Genre.java`**: Enumeration for book genres
- **`service/BookService.java`**: Business logic for filtering and retrieving books
- **`service/ReviewService.java`**: Business logic for retrieving and averaging reviews
- **`data/FakeDatabase.java`**: Simulates a simple in-memory database for books and reviews
- **`App.java`**: Application entry point with the console menu and main logic

---

## ▶️ How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/Pololac/BookReviewManager.git

2. Build and launch the application:
    ```bash
    cd BookReviewManager
    mvn compile
    mvn exec:java -Dexec.mainClass="App"

---
## 💡 Example Interaction
```bash
    List of books sorted by average rating:
    Book: The Hobbit - Average: 4.7
    Book: Dune - Average: 4.5
    ...
    
    Enter the author name:
    > Tolkien
    
    Reviews for books by Tolkien, sorted by date descending:
    Date: 2024-06-01 | Book ID: 1 | Comment: Amazing
    ...
    
    Top 3 books for genre: Fantasy
    - The Hobbit | Average: 4.7
    - Harry Potter | Average: 4.6
    ...
```
---
## 🎯 Concepts Demonstrated
- Java Stream API for mapping, grouping, sorting, filtering collections
- Use of Map, List, and custom classes for data modeling
- Separation of concerns by using services for books and reviews
---
Feel free to fork and adapt for your own library/book review needs!

