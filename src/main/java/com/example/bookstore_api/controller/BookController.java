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

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(@RequestParam(defaultValue = "0") Integer pageNo,
                                                  @RequestParam(defaultValue = "5") Integer pageSize) {
        List<Book> books = bookService.getAllBooks(pageNo, pageSize);
        return books.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(books);
    }

    @GetMapping("/{book_id}")
    public ResponseEntity<Book> getBookByBookId(@PathVariable String book_id) throws NoRelevantRecordFoundException {
        //try{
        Optional<Book> book = bookService.getBookById(book_id);
        return book.map(ResponseEntity::ok).get();
        //return book.map(ResponseEntity::ok).orElseThrow(() -> new NoRelevantRecordFoundException("No book found with given book id."));
//            if(!book.isEmpty()){
//                return  new ResponseEntity<>(book.get(),HttpStatus.FOUND);
//            }else
//            {
//                return  new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
//            }
        //return  book;
//        } catch (NoRelevantRecordFoundException e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
//
//        }
        //return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam(required = false) String title,
                                                  @RequestParam(required = false) String author,
                                                  @RequestParam(required = false) String publisher) throws NoRelevantRecordFoundException {
        List<Book> books;
        books = bookService.searchBooks(title, author, publisher);
        return ResponseEntity.ok(books);
        //return books.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(books);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Book>> filterBooks(@RequestParam(required = false) String title,
                                                  @RequestParam(required = false) String author,
                                                  @RequestParam(required = false) String publisher) throws NoRelevantRecordFoundException {
        List<Book> books;
        books = bookService.filterBooks(title, author, publisher);
        return ResponseEntity.ok(books);

    }


}
