package com.library.librarymanager.controller;

import com.library.librarymanager.model.LibraryUserDTO;
import com.library.librarymanager.repository.LibraryUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/libraryUser")
public class LibraryUserController {

    private final LibraryUserRepository libraryUserRepository;

    private LibraryUserController(LibraryUserRepository libraryUserRepository){
        this.libraryUserRepository =libraryUserRepository;
    }

    @GetMapping("/{requestedId}")
    private ResponseEntity<LibraryUserDTO> findById(@PathVariable Long requestedId){

        Optional<LibraryUserDTO> optionalLibraryUser = libraryUserRepository.findById(requestedId);
        if(optionalLibraryUser.isPresent()){
            return ResponseEntity.ok(optionalLibraryUser.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    private ResponseEntity<Void> createLibraryUser(@RequestBody LibraryUserDTO newLibraryUserDTO, UriComponentsBuilder uriBuilder){

        LibraryUserDTO libraryUserDTO = libraryUserRepository.save(newLibraryUserDTO);
        URI locationOfNewLibraryUser = uriBuilder
                .path("/libraryUser/{id}")
                .buildAndExpand(libraryUserDTO.id())
                .toUri();
        return ResponseEntity.created(locationOfNewLibraryUser).build();
    }

    @GetMapping()
    private ResponseEntity<Iterable<LibraryUserDTO>> getAllLibraryUsers(){
        return ResponseEntity.ok(libraryUserRepository.findAll());
    }
}
