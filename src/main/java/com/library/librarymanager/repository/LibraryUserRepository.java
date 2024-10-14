package com.library.librarymanager.repository;

import com.library.librarymanager.model.LibraryUserDTO;
import org.springframework.data.repository.CrudRepository;

public interface LibraryUserRepository extends CrudRepository <LibraryUserDTO, Long> {
}
