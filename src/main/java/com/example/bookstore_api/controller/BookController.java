package com.example.bookstore_api.controller;

import com.example.bookstore_api.exception.NoRelevantRecordFoundException;
import com.example.bookstore_api.model.Book;
import com.example.bookstore_api.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/books")

public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(produces = {"application/json", "application/xml"})
    public ResponseEntity<List<Book>> getAllBooks(@RequestParam(defaultValue = "0") Integer pageNo,
                                                  @RequestParam(defaultValue = "5") Integer pageSize) {
        List<Book> books = bookService.getAllBooks(pageNo, pageSize);
        return books.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(books);
    }

    @GetMapping(value = "/{book_id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<Book> getBookByBookId(@PathVariable String book_id) throws NoRelevantRecordFoundException {
        //try{
        Optional<Book> book = bookService.getBookById(book_id);
        return book.map(ResponseEntity::ok).get();
    }

    @GetMapping(value = "/search", produces = {"application/json", "application/xml"})
    public ResponseEntity<List<Book>> searchBooks(@RequestParam(required = false) String title,
                                                  @RequestParam(required = false) String author,
                                                  @RequestParam(required = false) String publisher) throws NoRelevantRecordFoundException {
        List<Book> books;
        books = bookService.searchBooks(title, author, publisher);
        return ResponseEntity.ok(books);
        //return books.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(books);
    }

    @GetMapping(value = "/filter", produces = {"application/json", "application/xml"})
    public ResponseEntity<List<Book>> filterBooks(@RequestParam(required = false) String title,
                                                  @RequestParam(required = false) String author,
                                                  @RequestParam(required = false) String publisher) throws NoRelevantRecordFoundException {
        List<Book> books;
        books = bookService.filterBooks(title, author, publisher);
        return ResponseEntity.ok(books);
    }

    @PostMapping(consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.createBook(book));
    }

}
