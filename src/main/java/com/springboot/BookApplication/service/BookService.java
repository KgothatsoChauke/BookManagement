package com.springboot.BookApplication.service;

import com.springboot.BookApplication.entity.Book;
import com.springboot.BookApplication.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
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

    //delete a book by id
    public void deleteBookById(Long id) {
        if(!bookRepository.existsById(id)){
            throw new RuntimeException("Book not found with id:" + id);
        }

        bookRepository.deleteById(id);
    }

    //update book
    public Book updateBook(Long id, Book updatedBook){
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Book not found with id: " + id));

        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setIsbn(updatedBook.getIsbn());
        existingBook.setGenre(updatedBook.getGenre());
        existingBook.setPublisher(updatedBook.getPublisher());
        existingBook.setPublicationYear(updatedBook.getPublicationYear());

        return bookRepository.save(existingBook);
    }
}
