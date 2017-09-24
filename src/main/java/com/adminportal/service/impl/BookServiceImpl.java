package com.adminportal.service.impl;

import com.adminportal.domain.Book;
import com.adminportal.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {


    @Autowired
    BookRepository bookRepository;

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return  bookRepository.findAll(); //casting into list of books no implementstion in bookrepo

    }

    public Book findOne(Long id){
        return  bookRepository.findOne(id);
    }

    @Override
    public void removeOne(Long id) {
        bookRepository.delete(id);
    }
}
