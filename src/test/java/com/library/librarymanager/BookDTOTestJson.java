package com.library.librarymanager;

import com.library.librarymanager.model.BookDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.core.io.ClassPathResource;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class BookDTOTestJson {

    @Autowired
    JacksonTester<BookDTO> json;

    @Test
    void bookSerialization() throws Exception{
        BookDTO bookDTO = new BookDTO(2L, "A arte da guerra", "Historic", "A description", 1500, "Sun Tzu");
        assertThat(json.write(bookDTO)).isStrictlyEqualToJson(new ClassPathResource("bookJson.json"));
        assertThat(json.write(bookDTO)).hasJsonPath("@.id");
        assertThat(json.write(bookDTO)).hasJsonPath("@.title");
        assertThat(json.write(bookDTO)).hasJsonPath("@.gender");
        assertThat(json.write(bookDTO)).hasJsonPath("@.description");
        assertThat(json.write(bookDTO)).hasJsonPath("@.yearOfPublication");
        assertThat(json.write(bookDTO)).hasJsonPath("@.author");
        assertThat(json.write(bookDTO)).extractingJsonPathNumberValue("@.id").isEqualTo(2);
        assertThat(json.write(bookDTO)).extractingJsonPathStringValue("@.title").isEqualTo("A arte da guerra");
        assertThat(json.write(bookDTO)).extractingJsonPathStringValue("@.gender").isEqualTo("Historic");
        assertThat(json.write(bookDTO)).extractingJsonPathStringValue("@.description").isEqualTo("A description");
        assertThat(json.write(bookDTO)).extractingJsonPathNumberValue("@.yearOfPublication").isEqualTo(1500);
        assertThat(json.write(bookDTO)).extractingJsonPathStringValue("@.author").isEqualTo("Sun Tzu");
    }

    @Test
    void bookDeserialization() throws Exception{
        String bookExpected = """
                    {
                        "id": 2,
                        "title": "A arte da guerra",
                        "gender": "Historic",
                        "description": "A description",
                        "yearOfPublication": 1500,
                        "author": "Sun Tzu"
                    }
                    """;
        assertThat(json.parse(bookExpected)).isEqualTo(new BookDTO(2L,"A arte da guerra", "Historic", "A description", 1500, "Sun Tzu"));
    }
}
