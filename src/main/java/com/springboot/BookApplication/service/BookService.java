package com.springboot.BookApplication.service;

import com.springboot.BookApplication.entity.Book;
import com.springboot.BookApplication.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    //save a book
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    //get book by id
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Book not found"));
    }

    //search books by title
    public List<Book> getBooksByTitle(String title){
        return bookRepository.findBooksByTitle(title);
    }

}
