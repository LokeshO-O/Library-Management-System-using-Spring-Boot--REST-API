
package org.example.librarymanagement.service;

import org.example.librarymanagement.model.Book;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    // ArrayList to store books
    private final List<Book> books = new ArrayList<>();

    // Automatically generate IDs
    private int nextId = 1;

    // 1. Add Book
    public Book addBook(Book book) {

        book.setId(nextId++);

        books.add(book);

        return book;
    }

    // 2. Get All Books
    public List<Book> getAllBooks() {

        return books;
    }

    // 3. Get Book By ID
    public Book getBookById(int id) {

        for (Book book : books) {

            if (book.getId() == id) {
                return book;
            }
        }

        return null;
    }

    // 4. Update Book
    public Book updateBook(int id, Book updatedBook) {

        for (Book book : books) {

            if (book.getId() == id) {

                book.setTitle(updatedBook.getTitle());
                book.setAuthor(updatedBook.getAuthor());
                book.setCategory(updatedBook.getCategory());

                book.setAvailableCopies(
                        updatedBook.getAvailableCopies()
                );

                return book;
            }
        }

        return null;
    }

    // 5. Delete Book
    public boolean deleteBook(int id) {

        return books.removeIf(
                book -> book.getId() == id
        );
    }
}
