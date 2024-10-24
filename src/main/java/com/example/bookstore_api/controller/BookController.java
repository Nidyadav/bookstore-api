package com.example.bookstore_api.controller;

import com.example.bookstore_api.model.Book;
import com.example.bookstore_api.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping("/ALL-BOOKS")
    public List<Book> getAllBooks(@RequestParam(defaultValue = "0") Integer pageNo,
                                  @RequestParam(defaultValue = "10") Integer pageSize){
       return bookService.getAllBooks(pageNo, pageSize);
    }
    @GetMapping("/book/{book_id}")
    public ResponseEntity<Book> getBookByBookId(@PathVariable String book_id){
       Optional<Book> book= bookService.getBookById(book_id);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/searchWithTitle")
    public List<Book> searchBookByTitle(@RequestParam  String title){
        return  bookService.searchBooksByTitle(title);
    }
    @GetMapping("/searchWithPublisher/{publisher}")
    public List<Book> filterBookByPublisher(@PathVariable String publisher){
        return bookService.filterBookByPublisher(publisher);

    }
    @GetMapping("/SearchWithAuthor/{author}")
    public List<Book> searchBooksByAuthor(@PathVariable String author){
        return  bookService.filterBooksByAuthor(author);
    }
    @GetMapping("/filter")
    public List<Book> filterBooksByPublisherAndAuthor( @RequestParam String publisher,@RequestParam String author){
        return  bookService.filterBooksByPublisherAndAuthor(publisher,author);
    }

}
