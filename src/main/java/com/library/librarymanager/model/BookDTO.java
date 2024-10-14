package com.library.librarymanager.model;

import org.springframework.data.annotation.Id;

public record BookDTO(@Id Long id, String title, String gender, String description, int yearOfPublication, String author) {
}
