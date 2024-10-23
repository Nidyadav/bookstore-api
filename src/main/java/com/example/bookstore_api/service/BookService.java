package com.example.bookstore_api.service;

import com.example.bookstore_api.model.Book;
import com.example.bookstore_api.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
    public Optional<Book>getBookById(String book_id){
        return bookRepository.findById(book_id);
    }
    public List<Book>searchBooksByTitle(String title){
        return  bookRepository.findByTitleIgnoreCase(title);
    }
    public List<Book> filterBookByPublisher(String publisher){
        return bookRepository.findByPublisher(publisher);
    }
    public List<Book> filterBooksByAuthor(String author){
        return  bookRepository.findByAuthor(author);
    }
}
