package com.springboot.BookApplication.controller;

import com.springboot.BookApplication.entity.Book;
import com.springboot.BookApplication.service.BookService;
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
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        Book savedBook = bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    //read a book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    //search a book by title
    @GetMapping("/search")
    public ResponseEntity<List<Book>> getBooksByTitle(@RequestParam String title){
        List<Book> books = bookService.getBooksByTitle(title);
        return ResponseEntity.ok(books);
    }

    //delete a book by id
    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Long id){
        bookService.deleteBookById(id);
    }


}
