
package org.example.librarymanagement.controller;

import org.example.librarymanagement.model.Book;
import org.example.librarymanagement.service.BookService;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    // Constructor Injection
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // 1. POST - Add Book
    @PostMapping
    public ResponseEntity<Book> addBook(
            @RequestBody Book book) {

        Book savedBook = bookService.addBook(book);

        return new ResponseEntity<>(
                savedBook, HttpStatus.CREATED
        );
    }

    // 2. GET - Get All Books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {

        return ResponseEntity.ok(
                bookService.getAllBooks()
        );
    }

    // 3. GET - Get Book By ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(
            @PathVariable int id) {

        Book book = bookService.getBookById(id);

        if (book == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(book);
    }

    // 4. PUT - Update Book
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(
            @PathVariable int id,
            @RequestBody Book book) {

        Book updatedBook =
                bookService.updateBook(id, book);

        if (updatedBook == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedBook);
    }

    // 5. DELETE - Delete Book
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(
            @PathVariable int id) {

        boolean deleted = bookService.deleteBook(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
