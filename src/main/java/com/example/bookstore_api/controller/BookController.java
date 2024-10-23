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
    public List<Book> getAllBooks(){
       return bookService.getAllBooks();
    }
    @GetMapping("/book/{book_id}")
    public ResponseEntity<Book> getBookByBookId(@PathVariable String book_id){
       Optional<Book> book= bookService.getBookById(book_id);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/search")
    public List<Book> searchBookByTitle(@RequestParam  String title){
        return  bookService.searchBooksByTitle(title);
    }
    @GetMapping("/publisher/{publisher}")
    public List<Book> filterBookByPublisher(@PathVariable String publisher){
        return bookService.filterBookByPublisher(publisher);

    }
    @GetMapping("/author/{author}")
    public List<Book>filterBooksByAuthor(@PathVariable String author){
        return  bookService.filterBooksByAuthor(author);
    }

}
