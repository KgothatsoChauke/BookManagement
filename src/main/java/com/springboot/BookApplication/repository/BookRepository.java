package com.springboot.BookApplication.repository;

import com.springboot.BookApplication.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    public List<Book> findBooksByTitle(String title);
}
