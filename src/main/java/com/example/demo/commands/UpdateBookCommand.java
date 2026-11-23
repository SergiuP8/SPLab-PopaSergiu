package com.example.demo.commands;

import com.example.demo.Book;

public class UpdateBookCommand implements Command<Book> {

    private final CommandContext context;
    private final Integer id;
    private final String title;

    public UpdateBookCommand(CommandContext context, Integer id, String title) {
        this.context = context;
        this.id = id;
        this.title = title;
    }

    @Override
    public Book execute() {
        Book book = context.getBooksRepository().findById(id).orElse(null);

        if (book == null) {
            return null;
        }

        book.setTitle(title);

        return context.getBooksRepository().save(book);
    }
}