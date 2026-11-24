package com.example.demo.controllers;

import com.example.demo.Book;
import com.example.demo.NewBookRequest;
import com.example.demo.commands.*;
import com.example.demo.observer.AllBooksSubject;
import com.example.demo.persistence.*;
import com.example.demo.commands.CreateBookCommand;
import com.example.demo.commands.DeleteBookCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BooksController {

    private final com.example.demo.commands.CommandContext commandContext;
    private final SynchronousCommandExecutor synchronousCommandExecutor;
    private final AsynchronousCommandExecutor asynchronousCommandExecutor;

    public BooksController(
            BooksRepositoryAdapter booksRepositoryAdapter,
            SynchronousCommandExecutor synchronousCommandExecutor,
            AsynchronousCommandExecutor asynchronousCommandExecutor) {

        this.commandContext = new com.example.demo.commands.CommandContext(booksRepositoryAdapter);
        this.synchronousCommandExecutor = synchronousCommandExecutor;
        this.asynchronousCommandExecutor = asynchronousCommandExecutor;
    }

    @GetMapping
    public List<Book> getAllBooks() {

        GetAllBooksCommand command =
                new GetAllBooksCommand(commandContext);

        return synchronousCommandExecutor.execute(command).join();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Integer id) {

        GetBookByIdCommand command =
                new GetBookByIdCommand(commandContext, id);

        return synchronousCommandExecutor.execute(command).join();
    }


    @PutMapping("/{id}")
    public Book updateBook(
            @PathVariable Integer id,
            @RequestBody String title) {

        UpdateBookCommand command =
                new UpdateBookCommand(commandContext, id, title);

        return synchronousCommandExecutor.execute(command).join();
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Integer id) {

        DeleteBookCommand command =
                new DeleteBookCommand(commandContext, id);

        return synchronousCommandExecutor.execute(command).join();
    }

    @Autowired
    private AllBooksSubject allBooksSubject;
    private BooksRepository booksRepository;
    @PostMapping
    public String newBook(@RequestBody NewBookRequest newBookRequest) {
        CreateBookCommand command = new CreateBookCommand(commandContext, newBookRequest.getTitle());
        Book book = command.execute();
        allBooksSubject.add(book);
        return "Book saved [" + book.getId() + "] " + book.getTitle();
    }
}