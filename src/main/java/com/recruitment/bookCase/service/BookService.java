package com.recruitment.bookCase.service;

import com.recruitment.bookCase.model.Book;

import java.util.List;

public interface BookService {

    Book findById(Long Id);
    Book findByTitle(String title);
    void saveBook(Book book);
    void updateBook(Book book);
    void deleteById(Long Id);
    List<Book> findAll();
    boolean isBookExist(Book book);
}
