package com.library.librarymanager.controllerTest;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.library.librarymanager.model.LibraryUser;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.net.URI;

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

    @DirtiesContext
    @Test
    void shouldCreateANewLibraryUser(){
        LibraryUser newLibraryUser = new LibraryUser(null,"Pedro");
        ResponseEntity<Void> createLibraryUser = restTemplate
                .postForEntity("/libraryUser", newLibraryUser, Void.class);
        assertThat(createLibraryUser.getStatusCode())
                .isEqualTo(HttpStatus.CREATED);

        URI locationOfNewLibraryUser =
                createLibraryUser.getHeaders().getLocation();
        ResponseEntity<String> getLibraryUserResponse = restTemplate.getForEntity(locationOfNewLibraryUser, String.class);
        assertThat(getLibraryUserResponse.getStatusCode())
                .isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldReturnAllLibraryUsers(){
        ResponseEntity<String> getAllLibraryUsersResponse = restTemplate
                .getForEntity("/libraryUser",String.class);
        assertThat(getAllLibraryUsersResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.
                parse(getAllLibraryUsersResponse.getBody());

        int libraryUsersCounter = documentContext.read("$.length()");
        assertThat(libraryUsersCounter).isEqualTo(3);

        JSONArray ids = documentContext.read("$..id");
        assertThat(ids).containsExactlyInAnyOrder(2, 3, 4);

        JSONArray userNames = documentContext.read("$..userName");
        assertThat(userNames).containsExactlyInAnyOrder("Joao", "Pedro", "Marta");
    }

}
