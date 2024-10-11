package com.library.librarymanager.controller;

import com.library.librarymanager.Book;
import com.library.librarymanager.LibraryUser;
import com.library.librarymanager.repository.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookRepository bookRepository;

    private BookController(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @GetMapping("{requestId}")
    private ResponseEntity<Book> findById(@PathVariable Long requestId){
        Optional<Book> book = bookRepository.findById(requestId);
        if(book.isPresent()){
            return ResponseEntity.ok(book.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
