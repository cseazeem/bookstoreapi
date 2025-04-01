package com.cseazeem.bookstoreapi;

import com.cseazeem.bookstoreapi.model.Book;
import com.cseazeem.bookstoreapi.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BookService bookService;

    @Test
    public void testCreateAndGetBook() {
        Book book = new Book("Test Book", "Test Author", 29.99);
        ResponseEntity<Book> createResponse = restTemplate.postForEntity("/books", book, Book.class);
        assertEquals(HttpStatus.CREATED, createResponse.getStatusCode());
        Book createdBook = createResponse.getBody();
        assertNotNull(createdBook.getId());

        ResponseEntity<Book> getResponse = restTemplate.getForEntity("/books/" + createdBook.getId(), Book.class);
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertEquals("Test Book", getResponse.getBody().getTitle());
    }

    @Test
    public void testGetNonExistentBook() {
        ResponseEntity<String> response = restTemplate.getForEntity("/books/999", String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().contains("Book with ID 999 not found"));
    }

    @Test
    public void testCreateInvalidBook() {
        Book invalidBook = new Book("", "", -5.0); // Invalid data
        ResponseEntity<String> response = restTemplate.postForEntity("/books", invalidBook, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().contains("Title is mandatory"));
        assertTrue(response.getBody().contains("Price must be positive"));
    }

    @Test
    public void testUpdateBook() {
        Book book = new Book("Old Title", "Old Author", 10.0);
        Book createdBook = bookService.createBook(book);

        Book updatedBook = new Book("New Title", "New Author", 15.0);
        ResponseEntity<Book> response = restTemplate.exchange(
                "/books/" + createdBook.getId(), HttpMethod.PUT, new HttpEntity<>(updatedBook), Book.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("New Title", response.getBody().getTitle());
    }

    @Test
    public void testDeleteBook() {
        Book book = new Book("Delete Me", "Author", 5.0);
        Book createdBook = bookService.createBook(book);

        ResponseEntity<Void> response = restTemplate.exchange(
                "/books/" + createdBook.getId(), HttpMethod.DELETE, null, Void.class);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        ResponseEntity<String> getResponse = restTemplate.getForEntity("/books/" + createdBook.getId(), String.class);
        assertEquals(HttpStatus.NOT_FOUND, getResponse.getStatusCode());
    }
}