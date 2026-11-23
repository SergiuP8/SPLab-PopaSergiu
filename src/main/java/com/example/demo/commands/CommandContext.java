package com.example.demo.commands;

import com.example.demo.Book;
import com.example.demo.persistence.CrudRepository;

public class CommandContext {

    private final CrudRepository<Book, Integer> booksRepository;

    public CommandContext(CrudRepository<Book, Integer> booksRepository) {
        this.booksRepository = booksRepository;
    }

    public CrudRepository<Book, Integer> getBooksRepository() {
        return booksRepository;
    }
}