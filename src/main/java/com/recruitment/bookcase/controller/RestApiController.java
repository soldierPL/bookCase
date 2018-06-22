package com.recruitment.bookcase.controller;

import java.util.List;

import com.recruitment.bookcase.model.Book;
import com.recruitment.bookcase.service.BookService;
import com.recruitment.bookcase.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api")
public class RestApiController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestApiController.class);

	private BookService bookService;

	@Autowired
	public RestApiController(BookService bookService) {
		this.bookService = bookService;
	}

	// -------------------Retrieve All Books------------------------------------------ //

	@RequestMapping(value = "/book/", method = RequestMethod.GET)
	public ResponseEntity<List<Book>> listAllBooks() {
		List<Book> books = bookService.findAllBooks();
		if (books.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(books, HttpStatus.OK);
	}

	// -------------------Retrieve Single Book---------------------------------------- //

	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getBook(@PathVariable("id") long id) {
		LOGGER.info("Fetching Book with id {}", id);
		Book book = bookService.findById(id);
		if (book == null) {
			LOGGER.error("Book with id {} not found.", id);
			return new ResponseEntity<>(new CustomErrorType("Book with id " + id
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(book, HttpStatus.OK);
	}

	// -------------------Create a Book----------------------------------------------- //

	@RequestMapping(value = "/book/", method = RequestMethod.POST)
	public ResponseEntity<?> createBook(@RequestBody Book book, UriComponentsBuilder ucBuilder) {
		LOGGER.info("Creating book : {}", book);

		if (bookService.isBookExist(book)) {
			LOGGER.error("Unable to create. A book with title '{}' already exist", book.getTitle());
			return new ResponseEntity<>(new CustomErrorType
					("Unable to create. A book with title " +
			book.getTitle() + " already exist."),HttpStatus.CONFLICT);
		}
		bookService.saveBook(book);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/book/{id}").buildAndExpand(book.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a Book ---------------------------------------------- //

	@RequestMapping(value = "/book/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateBook(@PathVariable("id") long id, @RequestBody Book book) {
		LOGGER.info("Updating Book with id {}", id);

		Book currentBook = bookService.findById(id);

		if (currentBook == null) {
			LOGGER.error("Unable to update. Book with id {} not found.", id);
			return new ResponseEntity<>(new CustomErrorType
					("Unable to update. Book with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentBook.setTitle(book.getTitle());
		currentBook.setDateOfPurchase(book.getDateOfPurchase());
		currentBook.setIsRead(book.getIsRead());

		bookService.updateBook(currentBook);
		return new ResponseEntity<>(currentBook, HttpStatus.OK);
	}

	// ------------------- Delete a Book ---------------------------------------------- //

	@RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteBook(@PathVariable("id") long id) {
		LOGGER.info("Fetching & Deleting Book with id {}", id);

		Book book = bookService.findById(id);
		if (book == null) {
			LOGGER.error("Unable to delete. Book with id {} not found.", id);
			return new ResponseEntity<>(new CustomErrorType("Unable to delete. Book with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		bookService.deleteBookById(id);
		return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
	}
}