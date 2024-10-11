package com.library.librarymanager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class LibraryUserTestJson {

    @Autowired
    private JacksonTester<LibraryUser> json;

    @Test
    void userSerializationTest() throws IOException {
        LibraryUser libraryUser = new LibraryUser(2L, "Joao");
        assertThat(json.write(libraryUser)).isStrictlyEqualToJson(new ClassPathResource("libraryUser.json"));
        assertThat(json.write(libraryUser)).hasJsonPath("@.id");
        assertThat(json.write(libraryUser)).hasJsonPath("@.userName");
        assertThat(json.write(libraryUser)).extractingJsonPathNumberValue("@.id").isEqualTo(2);
        assertThat(json.write(libraryUser)).extractingJsonPathStringValue("@.userName").isEqualTo("Joao");
    }

    @Test
    void userDeserialization() throws  IOException {
        String libraryUserExpected = """
                {
                    "id": 2,
                    "userName": "Joao"
                }
                """;
        assertThat(json.parse(libraryUserExpected)).isEqualTo(new LibraryUser(2L, "Joao"));
        assertThat(json.parseObject(libraryUserExpected).id()).isEqualTo(2L);
        assertThat(json.parseObject(libraryUserExpected).userName()).isEqualTo("Joao");
    }
}
