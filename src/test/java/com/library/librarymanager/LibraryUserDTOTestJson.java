package com.library.librarymanager;

import com.library.librarymanager.model.LibraryUserDTO;
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
public class LibraryUserDTOTestJson {

    @Autowired
    private JacksonTester<LibraryUserDTO> json;

    @Autowired JacksonTester<LibraryUserDTO[]> jsonGroup;

    private LibraryUserDTO[] libraryUserDTOS;

    @BeforeEach
    void setUp(){
        libraryUserDTOS = Arrays.array(
            new LibraryUserDTO(3L,"Marco", EMPLOYEE),
            new LibraryUserDTO(4L,"Marta", EMPLOYEE),
            new LibraryUserDTO(5L,"Carolina", CUSTOMER));
    }

    @Test
    void libraryUserSerializationTest() throws IOException {
        LibraryUserDTO libraryUserDTO = new LibraryUserDTO(2L, "Joao", EMPLOYEE);
        assertThat(json.write(libraryUserDTO)).isStrictlyEqualToJson(new ClassPathResource("singleLibraryUser.json"));
        assertThat(json.write(libraryUserDTO)).hasJsonPath("@.id");
        assertThat(json.write(libraryUserDTO)).hasJsonPath("@.userName");
        assertThat(json.write(libraryUserDTO)).hasJsonPath("@.userRole");
        assertThat(json.write(libraryUserDTO)).extractingJsonPathNumberValue("@.id").isEqualTo(2);
        assertThat(json.write(libraryUserDTO)).extractingJsonPathStringValue("@.userName").isEqualTo("Joao");
        assertThat(json.write(libraryUserDTO)).extractingJsonPathStringValue("@.userRole").isEqualTo("EMPLOYEE");
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
        assertThat(json.parse(libraryUserExpected)).isEqualTo(new LibraryUserDTO(2L, "Joao",EMPLOYEE));
        assertThat(json.parseObject(libraryUserExpected).id()).isEqualTo(2L);
        assertThat(json.parseObject(libraryUserExpected).userName()).isEqualTo("Joao");
        assertThat(json.parseObject(libraryUserExpected).userRole()).isEqualTo(EMPLOYEE);
    }

    @Test
    void libraryUserSerialization() throws IOException {
        assertThat(jsonGroup.write(libraryUserDTOS)).isStrictlyEqualToJson(new ClassPathResource("groupLibraryUser.json"));
    }

}
