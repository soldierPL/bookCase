package com.recruitment.bookCase.service;

import com.recruitment.bookCase.model.Book;
import com.recruitment.bookCase.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book findById(Long id) {
        return bookRepository.getOne(id);
    }

    public Book findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    public void updateBook(Book book) {
        saveBook(book);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public boolean isBookExist(Book book) {
        return findByTitle(book.getTitle()) != null;
    }


}
