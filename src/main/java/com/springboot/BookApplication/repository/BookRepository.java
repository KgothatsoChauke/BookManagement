package com.springboot.BookApplication.repository;

import com.springboot.BookApplication.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    public List<Book> findBooksByTitle(String title);

}
