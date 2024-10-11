package com.library.librarymanager.controller;

import com.library.librarymanager.LibraryUser;
import com.library.librarymanager.repository.LibraryUserRepository;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/libraryUser")
public class LibraryUserController {

    private final LibraryUserRepository libraryUserRepository;

    private LibraryUserController(LibraryUserRepository libraryUserRepository){
        this.libraryUserRepository =libraryUserRepository;
    }

    @GetMapping("/{requestedId}")
    private ResponseEntity<LibraryUser> findById(@PathVariable Long requestedId){

        Optional<LibraryUser> optionalLibraryUser = libraryUserRepository.findById(requestedId);
        if(optionalLibraryUser.isPresent()){
            return ResponseEntity.ok(optionalLibraryUser.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
