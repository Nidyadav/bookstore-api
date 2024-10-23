package com.example.bookstore_api.repository;

import com.example.bookstore_api.model.Book;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,String> {
    List<Book> findByTitleIgnoreCase(String title);
    List<Book> findByAuthor(String author);
    List<Book> findByPublisher(String publisher);
}
