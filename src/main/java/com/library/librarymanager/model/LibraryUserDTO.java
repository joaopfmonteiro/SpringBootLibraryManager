package com.library.librarymanager.model;

import com.library.librarymanager.model.enums.Role;
import org.springframework.data.annotation.Id;

public record LibraryUserDTO(@Id Long id, String userName, Role userRole) {
}
