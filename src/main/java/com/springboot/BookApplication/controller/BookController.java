package com.springboot.BookApplication.controller;

import com.springboot.BookApplication.dto.BookRequestDto;
import com.springboot.BookApplication.dto.BookResponseDto;
import com.springboot.BookApplication.exception.ErrorResponse;
import com.springboot.BookApplication.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/books")
public class BookController {

    private final BookService bookService;

    //create a book
    @PostMapping
    public ResponseEntity<BookResponseDto> addBook(@Valid @RequestBody BookRequestDto requestDto){
        BookResponseDto bookResponseDto = bookService.addBook(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(bookResponseDto);
    }

    //read a book by ID
    @Operation(
            summary = "get a book by ID",
            description = "Retrieve a book using its unique ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Book found successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BookResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Book not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable Long id){
        BookResponseDto book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    //search a book by title
    @Operation(
            summary = "Get books by title",
            description = "Search a book or books by their title"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Books successfully",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = BookResponseDto.class)
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Book not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @GetMapping("/search")
    public ResponseEntity<List<BookResponseDto>> getBooksByTitle(@RequestParam String title){
        List<BookResponseDto> books = bookService.getBooksByTitle(title);
        return ResponseEntity.ok(books);
    }

    //delete a book by id
    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Long id){
        bookService.deleteBookById(id);
    }

    //update book
    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable Long id , @RequestBody BookRequestDto updatedBook){
        return ResponseEntity.ok(bookService.updateBook(id, updatedBook));
    }

}
