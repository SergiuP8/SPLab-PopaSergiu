package com.example.demo.observer;

import com.example.demo.Book;
import org.springframework.stereotype.Component;

@Component
public class AllBooksSubject extends Subject {

    public void add(Book book) {
        notifyObservers(book);
    }

    public Observer attach(Observer observer) {
        registerObserver(observer);
        return observer;
    }
}