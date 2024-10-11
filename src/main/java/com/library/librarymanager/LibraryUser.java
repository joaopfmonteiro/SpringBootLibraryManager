package com.library.librarymanager;

import org.springframework.data.annotation.Id;

public record LibraryUser(@Id Long id, String userName) {
}
