package com.library.librarymanager.model;

import org.springframework.data.annotation.Id;

public record LibraryUser(@Id Long id, String userName) {
}
