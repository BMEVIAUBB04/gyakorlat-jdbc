package hu.bme.aut.jdbc.domain;

import java.util.List;

public class BookDao implements AutoCloseable {

    public BookDao() {
        // TODO: connect to database
       
    }

    public List<Book> findAll() {
        // TODO: retrieve all elements from table
    	return List.of();
    }

    public void persist(Book book) {
        // TODO: insert into database
    }

    public void save(Book book) {
        // TODO: update
    }

    public void delete(Book book) {
        // TODO: delete from database)
    }

    @Override
    public void close() throws Exception {
        // TODO: close database connection
    }
}
