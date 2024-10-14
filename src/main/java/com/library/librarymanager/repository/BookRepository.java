package com.library.librarymanager.repository;

import com.library.librarymanager.model.BookDTO;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<BookDTO, Long> {
}
