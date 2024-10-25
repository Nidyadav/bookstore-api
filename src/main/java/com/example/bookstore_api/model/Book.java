package com.example.bookstore_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")

public class Book {
    @Id
    private String bookId;
    private String quantity;

    private String title;
    private String series;
    private String author;
    private String rating;

    @Column(columnDefinition = "TEXT")
    private String description;
    private String language;
    private String isbn;

    @ElementCollection
    @CollectionTable(name = "book_genres", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "genre")
    private List<String> genres;


    @ElementCollection
    @CollectionTable(name = "book_characters",
            joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "characters")
    private List<String> characters;

    private String bookForm;
    private String edition;
    private String pages;
    private String publisher;
    private LocalDate publishingDate;
    private LocalDate firstPublishDate;

    @ElementCollection
    @CollectionTable(name = "book_awards", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "awards")
    private List<String> awards;
    private String numRating;

    @ElementCollection
    @CollectionTable(name = "book_ratings_by_stars", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "ratings_by_stars")
    private List<String> ratingsByStars;
    private String likedPercent;

    @Column(columnDefinition = "TEXT")
    private String coverImg;
    private String bbeScore;
    private String bbeVotes;
    private String price;


}
