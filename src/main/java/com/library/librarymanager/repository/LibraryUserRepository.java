package com.library.librarymanager.repository;

import com.library.librarymanager.model.LibraryUser;
import org.springframework.data.repository.CrudRepository;

public interface LibraryUserRepository extends CrudRepository <LibraryUser, Long> {
}
