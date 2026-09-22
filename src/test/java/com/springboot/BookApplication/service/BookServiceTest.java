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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;

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

        when(bookRepository.findById(id)).thenReturn(Optional.empty());

        //Act and Assert
        assertThatThrownBy(()-> bookService.getBookById(id))
                .isInstanceOf(BookNotFoundException.class)
                .hasMessage("Book with id 999 not found");

        verify(bookRepository).findById(id);
    }

    @Test
    void getBooksByTitle_shouldReturnBookResponseDtoList(){

        //Arrange
        String title = "The Pragmatic Programmer";

        Book book1 = new Book();
        Book book2 = new Book();

        List<Book> books = List.of(book1, book2);

        BookResponseDto responseDto1 = new BookResponseDto();
        BookResponseDto responseDto2 = new BookResponseDto();

        List<BookResponseDto> bookResponseDtoList= List.of(responseDto1, responseDto2);

        when(bookRepository.findBooksByTitle(title)).thenReturn(books);
        when(bookMapper.toResponseDtoList(books)).thenReturn(bookResponseDtoList);

        //Act
        List<BookResponseDto> results = bookService.getBooksByTitle(title);

        //Assert
        assertThat(results).isSameAs(bookResponseDtoList);

        verify(bookRepository).findBooksByTitle(title);
        verify(bookMapper).toResponseDtoList(books);
    }

    @Test
    void getBooksByTittle_shouldThrowExceptionWhenBooksNotFound(){

        //Arrange
        String title = "Atomic Habits";

        when(bookRepository.findBooksByTitle(title)).thenReturn(List.of());


        //Act and Assert
        assertThatThrownBy(()-> bookService.getBooksByTitle(title))
                .isInstanceOf(BookNotFoundException.class)
                .hasMessage("Books with title 'Atomic Habits' not found");

        verify(bookRepository).findBooksByTitle(title);
    }

    @Test
    void deleteBookById_shouldDeleteBookWhenBookExists(){

        //Arrange
        Long id = 1L;

        when(bookRepository.existsById(id)).thenReturn(true);

        //Act
        bookService.deleteBookById(id);

        verify(bookRepository).existsById(id);
        verify(bookRepository).deleteById(id);

    }

    @Test
    void deleteBookById_shouldThrowExceptionWhenBookDoesNotExist(){

        //Arrange
        Long id = 999L;

        when(bookRepository.existsById(id)).thenReturn(false);

        //Act and assert
        assertThatThrownBy(()-> bookService.deleteBookById(id))
                .isInstanceOf(BookNotFoundException.class)
                .hasMessage("Book with id '999' does not exist");

        verify(bookRepository).existsById(id);
        verify(bookRepository, never()).deleteById(id);
    }
}
