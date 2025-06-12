package service;

import model.Book;
import model.Genre;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookService {

    private final List<Book> allBooks;

    public BookService(List<Book> allBooks) {
        this.allBooks = allBooks;
    }

    public List<Book> getAllBooks() {
        return allBooks;
    }

    public List<Book> getBooksFromAuthor(String author) {
        return allBooks.stream()
                .filter(book -> book.getAuthor().trim().toLowerCase().equals(author))
                .collect(Collectors.toList());
    }

    public Map<Genre, List<Book>> getBooksByGenre() {
        return allBooks.stream()
                .collect(Collectors.groupingBy(Book::getGenre));
    }

    public List<Book> getBooksPublishedBefore(int year) {
        return allBooks.stream()
                .filter(book -> book.getPublishYear() < year)
                .collect(Collectors.toList());
    }
}
