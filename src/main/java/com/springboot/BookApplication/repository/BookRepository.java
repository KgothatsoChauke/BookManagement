package com.springboot.BookApplication.repository;

import com.springboot.BookApplication.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
