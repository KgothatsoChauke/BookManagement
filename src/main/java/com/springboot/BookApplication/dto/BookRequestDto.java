package com.springboot.BookApplication.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookRequestDto {



    @NotBlank(message = "cannot be blank")
    private String title;

    @NotBlank(message = "cannot be blank")
    private String author;

    @NotBlank(message = "cannot be blank")
    @Pattern(regexp = "^(\\d{10}|\\d{13})$",
            message = "must contain 10 or 13 digits")
    private String isbn;

    @NotBlank(message = "cannot be blank")
    private String publisher;

    @Min(1900)
    private Integer publicationYear;
    private String genre;
}
