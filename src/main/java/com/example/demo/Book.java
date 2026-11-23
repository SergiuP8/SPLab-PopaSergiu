package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@jakarta.persistence.Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})


public class Book extends Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )

    private List<Author> authors = new ArrayList<>();

    public Book(String title) {
        super(title);
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public void addContent(Element element) {
        add(element);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void render() {
        System.out.println("Book: " + getTitle());
        System.out.println();

        System.out.println("Authors:");
        for (Author author : authors) {
            author.print();
        }

        System.out.println();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);

        for (int i = 0; ; i++) {
            try {
                get(i).accept(visitor);
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }
    }
}