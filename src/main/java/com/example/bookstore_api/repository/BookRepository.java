package com.example.bookstore_api.repository;

import com.example.bookstore_api.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface BookRepository extends JpaRepository<Book,String>, PagingAndSortingRepository<Book,String> {
    List<Book> findByTitleIgnoreCase(String title);
    List<Book> findByIsbn(String isbn);
    List<Book> findByAuthor(String author);
@Query("SELECT b FROM Book b WHERE b.publisher=:publisher OR b.author=:author")
    List<Book> filterBooksByPublisherAndAuthor(@Param("publisher") String publisher, @Param("author")String author);
}
