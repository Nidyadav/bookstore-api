package com.example.bookstore_api.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;


import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
@JacksonXmlRootElement(localName = "book")
@Valid
public class Book {
    @Id
    @Column(name = "book_id", nullable = false)
    private String bookId;    //bookId is not auto generated, it has to be manually set
    private String quantity;
    @NotNull(message = "title can not be null.")
    private String title;
    private String series;
    private String author;
    private String rating;

    @Column(columnDefinition = "TEXT")
    private String description;
    private String language;
    @Size(min=10, max=20 ,message = "isbn should be between 10 to 20 digits")
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
    @NotEmpty(message = "Price is required")
    private String price;


}
