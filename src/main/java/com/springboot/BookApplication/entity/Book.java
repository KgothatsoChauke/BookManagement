package com.springboot.BookApplication.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @NotBlank
    @Column(nullable = false)
    private String author;

    @NotBlank
    @Pattern(regexp = "^(\\d{10}|\\d{13})$",
            message = "ISBN must contain 10 or 13 digits")

    @Column(unique = true)
    private String isbn;

    @NotBlank
    private String publisher;

    @Min(1900)
    private Integer publicationYear;
    private String genre;

}
