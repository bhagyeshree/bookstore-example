package com.cognizant.com.bookstore;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void listReturnEmptyListOfBooks() throws Exception{

        //Exercise
        final String  responseContent = mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        //Assert
        assertThat(responseContent, is("[]"));

    }

    @Test
    public void listReturnListOfOneBook() throws Exception{

        //SetUp

        Book altered_carbon = new Book("Altered Carbon", "Richard Morgan");
        bookRepository.save(altered_carbon);

        //Exercise
        final String responseContent = mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        List<Book> actual = OBJECT_MAPPER.readValue(responseContent, new TypeReference<List<Book>>(){});

        //Assert
        assertThat(actual, contains(altered_carbon));

    }


}
