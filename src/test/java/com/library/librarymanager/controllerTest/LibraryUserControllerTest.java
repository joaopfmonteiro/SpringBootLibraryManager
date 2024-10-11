package com.library.librarymanager.controllerTest;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.library.librarymanager.LibraryUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LibraryUserControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldReturnALibraryUser(){
        ResponseEntity<String> libraryUserResponseEntity = restTemplate
                .getForEntity("/libraryUser/2", String.class);
        assertThat(libraryUserResponseEntity.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(libraryUserResponseEntity.getBody());
        Number id = documentContext.read("$.id");
        String userName = documentContext.read("$.userName");
        assertThat(id).isEqualTo(2);
        assertThat(userName).isEqualTo("Joao");
//        assertThat(libraryUserResponseEntity.getBody()).isEqualTo()
    }

    @Test
    void shouldNotReturnALibraryUser(){
        ResponseEntity<String> libraryUserResponseEntity = restTemplate
                .getForEntity("/libraryUser/99", String.class);
        assertThat(libraryUserResponseEntity.getStatusCode())
                .isEqualTo(HttpStatus.NOT_FOUND);
    }
}
