package com.library.librarymanager;

import org.springframework.data.annotation.Id;

public record Book(@Id Long id, String title, String gender, String description, int yearOfPublication, String author) {
}
