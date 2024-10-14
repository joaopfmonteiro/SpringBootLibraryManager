package com.library.librarymanager.controllerTest;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookDTOControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldReturnALibraryUser(){
        ResponseEntity<String> libraryUserResponseEntity = restTemplate
                .getForEntity("/book/2", String.class);
        assertThat(libraryUserResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(libraryUserResponseEntity.getBody());
        Number id = documentContext.read("$.id");
        String title = documentContext.read("$.title");
        String gender = documentContext.read("$.gender");
        String description = documentContext.read("$.description");
        Number yearOfPublication =  documentContext.read(("$.yearOfPublication"));
        String author = documentContext.read("$.author");
        assertThat(id).isEqualTo(2);
        assertThat(title).isEqualTo("A arte da guerra");
        assertThat(gender).isEqualTo("Historic");
        assertThat(description).isEqualTo("A description");
        assertThat(yearOfPublication).isEqualTo(1500);
        assertThat(author).isEqualTo("Sun Tzu");
    }

    @Test
    void shouldNotReturnALibraryUser(){
        ResponseEntity<String> libraryUserResponseEntity = restTemplate
                .getForEntity("/book/20", String.class);
        assertThat(libraryUserResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
