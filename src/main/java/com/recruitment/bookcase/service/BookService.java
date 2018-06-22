package com.recruitment.bookcase.service;

import java.util.List;

import com.recruitment.bookcase.model.Book;

public interface BookService {

	Book findById(Long id);

	Book findByTitle(String title);

	void saveBook(Book book);

	void updateBook(Book book);

	void deleteBookById(Long id);

	List<Book> findAllBooks();

	boolean isBookExist(Book book);}