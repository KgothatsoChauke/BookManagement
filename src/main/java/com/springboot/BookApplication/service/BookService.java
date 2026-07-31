package com.springboot.BookApplication.service;

import com.springboot.BookApplication.entity.Book;
import com.springboot.BookApplication.exception.BookNotFoundException;
import com.springboot.BookApplication.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
                .orElseThrow(()-> new BookNotFoundException("Book with id " + id +" not found"));
    }

    //search books by title
    public List<Book> getBooksByTitle(String title){
        List<Book> books = bookRepository.findBooksByTitle(title);

        if(books.isEmpty()){
            throw new BookNotFoundException("Books with title '"+ title + "' not found");
         }

        return books;
    }

    //delete a book by id
    public void deleteBookById(Long id) {
        if(!bookRepository.existsById(id)){
            throw new BookNotFoundException("Book with id '" + id + "' does not exist");
        }

        bookRepository.deleteById(id);
    }

    //update book
    public Book updateBook(Long id, Book updatedBook){
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(()-> new BookNotFoundException("Book with id '" + id + "' not found"));

        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setIsbn(updatedBook.getIsbn());
        existingBook.setGenre(updatedBook.getGenre());
        existingBook.setPublisher(updatedBook.getPublisher());
        existingBook.setPublicationYear(updatedBook.getPublicationYear());

        return bookRepository.save(existingBook);
    }
}
