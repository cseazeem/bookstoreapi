package com.cseazeem.bookstoreapi.repository;


import com.cseazeem.bookstoreapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
