package com.springboot.BookApplication.service;

import com.springboot.BookApplication.dto.BookRequestDto;
import com.springboot.BookApplication.dto.BookResponseDto;
import com.springboot.BookApplication.entity.Book;
import com.springboot.BookApplication.exception.BookNotFoundException;
import com.springboot.BookApplication.mapper.BookMapper;
import com.springboot.BookApplication.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookService bookService;

    @Test
    void addBook_shouldReturnBookResponseDto(){

       //Arrange
        BookRequestDto requestDto = new BookRequestDto();
        Book book = new Book();
        Book savedBook = new Book();
        BookResponseDto responseDto = new BookResponseDto();

        when(bookMapper.toEntity(requestDto)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(savedBook);
        when(bookMapper.toResponseDto(savedBook)).thenReturn(responseDto);

        //Act
        BookResponseDto result = bookService.addBook(requestDto);

        //Assert
        assertThat(result).isSameAs(responseDto);

        verify(bookMapper).toEntity(requestDto);
        verify(bookRepository).save(book);
        verify(bookMapper).toResponseDto(savedBook);
    }

    @Test
    void getBookById_shouldReturnBookResponseDto(){

        //Arrange
        Long id = 1L;
        Book book = new Book();
        BookResponseDto responseDto = new BookResponseDto();

        when(bookRepository.findById(id)).thenReturn(Optional.of(book));
        when(bookMapper.toResponseDto(book)).thenReturn(responseDto);

        //Act
        BookResponseDto result = bookService.getBookById(id);

        //Assert
        assertThat(result).isSameAs(responseDto);

        verify(bookRepository).findById(id);
        verify(bookMapper).toResponseDto(book);
    }

    @Test
    void getBookById_shouldThrowExceptionWhenBookDoesNotExist(){

        //Arrange
        Long id = 999L;
        Book book = new Book();
        BookResponseDto responseDto = new BookResponseDto();

        when(bookRepository.findById(id)).thenReturn(Optional.empty());

        //Act and Assert
        assertThatThrownBy(()-> bookService.getBookById(id))
                .isInstanceOf(BookNotFoundException.class)
                .hasMessage("Book with id 999 not found");

        verify(bookRepository).findById(id);
    }

}
