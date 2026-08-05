package com.springboot.BookApplication.service;

import com.springboot.BookApplication.dto.BookRequestDto;
import com.springboot.BookApplication.dto.BookResponseDto;
import com.springboot.BookApplication.entity.Book;
import com.springboot.BookApplication.exception.BookNotFoundException;
import com.springboot.BookApplication.mapper.BookMapper;
import com.springboot.BookApplication.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    //save a book
    public BookResponseDto addBook(BookRequestDto requestDto) {

        //convert DTO to Entity
        Book book = bookMapper.toEntity(requestDto);

        //save book Entity
        Book savedBook = bookRepository.save(book);

        //convert Entity to response DTO
        return bookMapper.toResponseDto(savedBook);
    }

    //get book by id
    public BookResponseDto getBookById(Long id) {

        Book book = bookRepository.findById(id)
                .orElseThrow(()-> new BookNotFoundException("Book with id " + id +" not found"));

        return bookMapper.toResponseDto(book);
    }

    //search books by title
    public List<BookResponseDto> getBooksByTitle(String title){
        List<Book> books = bookRepository.findBooksByTitle(title);

        if(books.isEmpty()){
            throw new BookNotFoundException("Books with title '"+ title + "' not found");
         }

        return bookMapper.toResponseDtoList(books);
    }

    //delete a book by id
    public void deleteBookById(Long id) {
        if(!bookRepository.existsById(id)){
            throw new BookNotFoundException("Book with id '" + id + "' does not exist");
        }

        bookRepository.deleteById(id);
    }

    //update book
    public BookResponseDto updateBook(Long id, BookRequestDto updatedBook){
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(()-> new BookNotFoundException("Book with id '" + id + "' not found"));

        bookMapper.updateEntityFromDto(updatedBook, existingBook);

        Book savedBook = bookRepository.save(existingBook);

        return bookMapper.toResponseDto(savedBook);
    }
}
