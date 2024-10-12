package com.library.librarymanager.repository;

import com.library.librarymanager.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
