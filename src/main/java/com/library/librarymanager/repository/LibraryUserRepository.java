package com.library.librarymanager.repository;

import com.library.librarymanager.LibraryUser;
import org.springframework.data.repository.CrudRepository;

public interface LibraryUserRepository extends CrudRepository <LibraryUser, Long> {
}
