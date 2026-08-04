package com.springboot.BookApplication.mapper;

import com.springboot.BookApplication.dto.BookRequestDto;
import com.springboot.BookApplication.dto.BookResponseDto;
import com.springboot.BookApplication.entity.Book;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class BookMapper {

    public BookResponseDto toResponseDto(Book book){
        BookResponseDto responseDto = new BookResponseDto();

        responseDto.setId(book.getId());
        responseDto.setTitle(book.getTitle());
        responseDto.setAuthor(book.getAuthor());
        responseDto.setIsbn(book.getIsbn());
        responseDto.setPublisher(book.getPublisher());
        responseDto.setPublicationYear(book.getPublicationYear());

        return responseDto;
    }

    public Book toEntity(BookRequestDto requestDto){
        Book entity = new Book();

        entity.setTitle(requestDto.getTitle());
        entity.setAuthor(requestDto.getAuthor());
        entity.setIsbn(requestDto.getIsbn());
        entity.setPublisher(requestDto.getPublisher());
        entity.setPublicationYear(requestDto.getPublicationYear());
        entity.setGenre(requestDto.getGenre());

        return  entity;
    }

    public List<BookResponseDto> toResponseDtoList(List<Book> books){

        return books.stream()
                .map(book -> toResponseDto(book))
                .toList();
    }
}
