package com.springboot.BookApplication.mapper;

import com.springboot.BookApplication.dto.BookRequestDto;
import com.springboot.BookApplication.dto.BookResponseDto;
import com.springboot.BookApplication.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookResponseDto toResponseDto(Book book);

    @Mapping(target = "id", ignore = true)
    Book toEntity(BookRequestDto requestDto);

    List<BookResponseDto> toResponseDtoList(List<Book> books);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(BookRequestDto dto,@MappingTarget Book book);
}
