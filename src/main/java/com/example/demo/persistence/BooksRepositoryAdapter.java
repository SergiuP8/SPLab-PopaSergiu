package com.example.demo.persistence;

import com.example.demo.Book;
import com.example.demo.persistence.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.persistence.BooksRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class BooksRepositoryAdapter implements CrudRepository<Book, Integer> {

    private final BooksRepository booksRepository;

    public BooksRepositoryAdapter(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Override
    public Book save(Book entity) {
        return booksRepository.save(entity);
    }

    @Override
    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Integer id) {
        return booksRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        booksRepository.deleteById(id);
    }
}