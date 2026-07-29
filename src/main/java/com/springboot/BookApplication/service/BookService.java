package com.springboot.BookApplication.service;

import com.springboot.BookApplication.entity.Book;
import com.springboot.BookApplication.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    //save a book
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }
}
