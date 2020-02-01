package hu.bme.aut.jdbc.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookDao implements AutoCloseable {

    private static Long idCounter = 1l;

    private List<Book> items = new ArrayList<>();

    // TODO: create database

    public BookDao() {
        // TODO: connect to database
        // TODO: CREATE TABLE IF NOT EXIST

        /*
        CREATE TABLE IF NOT EXISTS books (
            id int IDENTITY(1,1) PRIMARY KEY,
            author nvarchar(255) NOT NULL,
            title nvarchar(255) NOT NULL,
            type nvarchar(10) NOT NULL,
            description text)
         */
    }

    public List<Book> findAll() {
        // TODO: retrieve all elements from table
        return Collections.unmodifiableList(items);
    }

    public void persist(Book book) {
        // TODO: insert into database
        book.setId(idCounter++);
        items.add(book);
    }

    public void save(Book book) {
        // TODO: update
        int idx = items.indexOf(book);
        items.set(idx, book);
    }

    public void delete(Book book) {
        // TODO: delete from database
        items.remove(book);
    }

    @Override
    public void close() throws Exception {
        // TODO: close database connection
    }
}
