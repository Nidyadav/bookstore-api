package com.example.bookstore_api.repository;

import com.example.bookstore_api.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, String>, PagingAndSortingRepository<Book, String> {


    @Query("SELECT bb FROM Book bb WHERE bb.title = :title AND bb.author = :author AND bb.publisher = :publisher")
    List<Book> searchBooks(@Param("title") String title, @Param("author") String author, @Param("publisher") String publisher);
    @Query("SELECT bb FROM Book bb WHERE bb.title = :title OR bb.author = :author OR bb.publisher = :publisher")
    List<Book> filterBooks(@Param("title") String title, @Param("author") String author, @Param("publisher") String publisher);
}

