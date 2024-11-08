package com.example.bookstore_api.service;

import com.example.bookstore_api.exception.NoRelevantRecordFoundException;
import com.example.bookstore_api.model.Book;
import com.example.bookstore_api.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks(int pageNo, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by("rating").descending());
        Page<Book> pagingBook = bookRepository.findAll(pageRequest);
        return pagingBook.getContent();
    }

    public Optional<Book> getBookById(String book_id) throws NoRelevantRecordFoundException {
        Optional<Book> book = bookRepository.findById(book_id);
        if (book.isEmpty()) {
            throw new NoRelevantRecordFoundException("No relevant book found with given book id, try again.");
        }
        return book;
        //return bookRepository.findById(book_id);
    }

    public List<Book> searchBooks(String title, String author, String publisher) throws NoRelevantRecordFoundException {
        List<Book> books = bookRepository.searchBooks(title, author, publisher);
        if (books.isEmpty()) {
            throw new NoRelevantRecordFoundException("No books found matching your search criteria, try again.");
        }
        return books;
    }
    public List<Book> filterBooks(String title, String author, String publisher) throws NoRelevantRecordFoundException {
        List<Book> books = bookRepository.filterBooks(title, author, publisher);
        if (books.isEmpty()) {
            throw new NoRelevantRecordFoundException("No books found matching your filter criteria, try again.");
        }
        return books;
    }


}
