package com.example.bookstore_api.controller;

import com.example.bookstore_api.model.Book;
import com.example.bookstore_api.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(@RequestParam(defaultValue = "0") Integer pageNo,
                                  @RequestParam(defaultValue = "5") Integer pageSize) {
        List<Book> books= bookService.getAllBooks(pageNo, pageSize);
        return books.isEmpty()? ResponseEntity.noContent().build():ResponseEntity.ok(books);
    }

    @GetMapping("/{book_id}")
    public ResponseEntity<Book> getBookByBookId(@PathVariable String book_id) {
        Optional<Book> book = bookService.getBookById(book_id);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBookByTitle(@RequestParam(required = false) String title,
                                                        @RequestParam(required = false) String author,
                                                        @RequestParam(required = false) String isbn) {
        List<Book> books;
        if (title != null) {
            books=bookService.searchBooksByTitle(title);
        } else if (author!=null) {
            books=bookService.searchBookByAuthor(author);
        } else if (isbn!=null) {
            books= bookService.searchBooksByIsbn(isbn);
        }else{
            return  ResponseEntity.badRequest().build();
        }
       return books.isEmpty()? ResponseEntity.noContent().build():ResponseEntity.ok(books);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Book>> filterBooksByPublisherAndAuthor(@RequestParam(required = false) String publisher,
                                                                      @RequestParam(required = false) String author) {
        if (publisher == null && author == null) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }
        List<Book> books = bookService.filterBooksByPublisherAndAuthor(publisher, author);
        return books.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(books);
    }

}
