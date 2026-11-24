package com.example.demo.observer;

import com.example.demo.Book;
import java.util.ArrayList;
import java.util.List;

public class Subject {
    protected List<Observer> observerCollection = new ArrayList<>();

    public void registerObserver(Observer observer) {
        observerCollection.add(observer);
    }

    public void unregisterObserver(Observer observer) {
        observerCollection.remove(observer);
    }

    public void notifyObservers(Book book) {
        for (Observer observer : observerCollection) {
            observer.update(book);
        }
    }
}