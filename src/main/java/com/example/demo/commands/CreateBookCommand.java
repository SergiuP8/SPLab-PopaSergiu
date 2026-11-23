package com.example.demo.commands;

import com.example.demo.Book;

public class CreateBookCommand implements Command<Book> {

    private final CommandContext context;
    private final String title;

    public CreateBookCommand(CommandContext context, String title) {
        this.context = context;
        this.title = title;
    }

    @Override
    public Book execute() {
        Book book = new Book(title);

        book = context.getBooksRepository().save(book);

        return book;
    }
}