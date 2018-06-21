package com.recruitment.bookCase.controller;

import com.recruitment.bookCase.errors.OwnerError;
import com.recruitment.bookCase.model.Book;
import com.recruitment.bookCase.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("api")
public class RestApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestApiController.class);

    private BookService bookService;

    @Autowired
    public RestApiController(BookService bookService) {
        this.bookService = bookService;
    }

//    method below will find all books in list

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> listAllBooks() {
        List<Book> books = bookService.findAll();
        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

//    method below will find only one book from list

    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getBook(@PathVariable("id") long id) {
        LOGGER.info("Ooouh..., I found one with id {}", id);
        Book book = bookService.findById(id);
        if (book == null) {
            LOGGER.error("I didn`t find book with {} id number.", id);
            return new ResponseEntity<>(new OwnerError("Probably You don`t have book with " + id
                    + " id number"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

//    method below will add already created book to our book case

    @RequestMapping(value = "book/", method = RequestMethod.POST)
    public ResponseEntity<?> CreateAndAddBook(@RequestBody Book book, UriComponentsBuilder uriComponentsBuilder) {
        LOGGER.info("Creating a book: {}", book);

        if (bookService.isBookExist(book)) {
            LOGGER.error("I can`t create '{}', because it`s already exist", book.getTitle());
            return new ResponseEntity<>(new OwnerError("I can`t create " + book.getTitle() + "because it`s already exist."), HttpStatus.CONFLICT);
        }
        bookService.saveBook(book);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uriComponentsBuilder.path("/api/book/{id}").buildAndExpand(book.getId()).toUri());
        return new ResponseEntity<String>(httpHeaders, HttpStatus.CREATED);
    }

//        this method will update already existed book

    @RequestMapping(value = "/book/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateBook(@PathVariable("id") long id, @RequestBody Book book) {
        LOGGER.info("Updating Book with id {}", id);

        Book currentBook = bookService.findById(id);

        if (currentBook == null) {
            LOGGER.error("Unable to update. Book with id {} not found.", id);
            return new ResponseEntity<>(new OwnerError
                    ("Something went wrong! Book with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentBook.setTitle(book.getTitle());
        currentBook.setDateOfPurchase(book.getDateOfPurchase());
        currentBook.setRead(book.getRead());

        bookService.updateBook(currentBook);
        return new ResponseEntity<>(currentBook, HttpStatus.OK);
    }

//        this method will delete chosen book

    @RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteBook(@PathVariable("id") long id) {
        LOGGER.info("Deleting Book with id {}", id);

        Book book = bookService.findById(id);
        if (book == null) {
            LOGGER.error("Something went wrong!. Book with id {} not found.", id);
            return new ResponseEntity<>(new OwnerError("Unable to delete. Book with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        bookService.deleteById(id);
        return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
    }
}


