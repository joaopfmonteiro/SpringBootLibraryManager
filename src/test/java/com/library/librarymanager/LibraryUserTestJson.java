package com.library.librarymanager;

import com.library.librarymanager.model.LibraryUser;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static com.library.librarymanager.model.enums.Role.CUSTOMER;
import static com.library.librarymanager.model.enums.Role.EMPLOYEE;
import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class LibraryUserTestJson {

    @Autowired
    private JacksonTester<LibraryUser> json;

    @Autowired JacksonTester<LibraryUser[]> jsonGroup;

    private LibraryUser[] libraryUsers;

    @BeforeEach
    void setUp(){
        libraryUsers = Arrays.array(
            new LibraryUser(3L,"Marco", EMPLOYEE),
            new LibraryUser(4L,"Marta", EMPLOYEE),
            new LibraryUser(5L,"Carolina", CUSTOMER));
    }

    @Test
    void libraryUserSerializationTest() throws IOException {
        LibraryUser libraryUser = new LibraryUser(2L, "Joao", EMPLOYEE);
        assertThat(json.write(libraryUser)).isStrictlyEqualToJson(new ClassPathResource("singleLibraryUser.json"));
        assertThat(json.write(libraryUser)).hasJsonPath("@.id");
        assertThat(json.write(libraryUser)).hasJsonPath("@.userName");
        assertThat(json.write(libraryUser)).hasJsonPath("@.userRole");
        assertThat(json.write(libraryUser)).extractingJsonPathNumberValue("@.id").isEqualTo(2);
        assertThat(json.write(libraryUser)).extractingJsonPathStringValue("@.userName").isEqualTo("Joao");
        assertThat(json.write(libraryUser)).extractingJsonPathStringValue("@.userRole").isEqualTo("EMPLOYEE");
    }

    @Test
    void libraryUserDeserialization() throws  IOException {
        String libraryUserExpected = """
                {
                    "id": 2,
                    "userName": "Joao",
                    "userRole": "EMPLOYEE"
                }
                """;
        assertThat(json.parse(libraryUserExpected)).isEqualTo(new LibraryUser(2L, "Joao",EMPLOYEE));
        assertThat(json.parseObject(libraryUserExpected).id()).isEqualTo(2L);
        assertThat(json.parseObject(libraryUserExpected).userName()).isEqualTo("Joao");
        assertThat(json.parseObject(libraryUserExpected).userRole()).isEqualTo(EMPLOYEE);
    }

    @Test
    void libraryUserSerialization() throws IOException {
        assertThat(jsonGroup.write(libraryUsers)).isStrictlyEqualToJson(new ClassPathResource("groupLibraryUser.json"));
    }

}
