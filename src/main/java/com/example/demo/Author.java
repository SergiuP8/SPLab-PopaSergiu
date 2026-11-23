package com.example.demo;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;
import java.util.List;


@Entity
@Table(name = "authors")

public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

    public Author(String name) {
        this.name = name;
    }

    public void print() {
        System.out.println("Author: " + name);
    }
}