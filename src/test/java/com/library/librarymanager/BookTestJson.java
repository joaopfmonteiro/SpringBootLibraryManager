package com.library.librarymanager;

import com.library.librarymanager.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.core.io.ClassPathResource;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class BookTestJson {

    @Autowired
    JacksonTester<Book> json;

    @Test
    void bookSerialization() throws Exception{
        Book book = new Book(2L, "A arte da guerra", "Historic", "A description", 1500, "Sun Tzu");
        assertThat(json.write(book)).isStrictlyEqualToJson(new ClassPathResource("bookJson.json"));
        assertThat(json.write(book)).hasJsonPath("@.id");
        assertThat(json.write(book)).hasJsonPath("@.title");
        assertThat(json.write(book)).hasJsonPath("@.gender");
        assertThat(json.write(book)).hasJsonPath("@.description");
        assertThat(json.write(book)).hasJsonPath("@.yearOfPublication");
        assertThat(json.write(book)).hasJsonPath("@.author");
        assertThat(json.write(book)).extractingJsonPathNumberValue("@.id").isEqualTo(2);
        assertThat(json.write(book)).extractingJsonPathStringValue("@.title").isEqualTo("A arte da guerra");
        assertThat(json.write(book)).extractingJsonPathStringValue("@.gender").isEqualTo("Historic");
        assertThat(json.write(book)).extractingJsonPathStringValue("@.description").isEqualTo("A description");
        assertThat(json.write(book)).extractingJsonPathNumberValue("@.yearOfPublication").isEqualTo(1500);
        assertThat(json.write(book)).extractingJsonPathStringValue("@.author").isEqualTo("Sun Tzu");
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
        assertThat(json.parse(bookExpected)).isEqualTo(new Book(2L,"A arte da guerra", "Historic", "A description", 1500, "Sun Tzu"));
    }
}
