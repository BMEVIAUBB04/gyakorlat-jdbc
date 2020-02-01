package hu.bme.aut.jdbc.domain;

import java.io.Serializable;
import java.util.Objects;

public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum EntryType {
        PRINTED, EBOOK;
    }

    private Long id;
    private String author;
    private String title;
    private EntryType type;
    private String desc;

    public Book() {
    }

    public Book(String author, String title, EntryType type,
                String desc) {
        this.author = author;
        this.title = title;
        this.type = type;
        this.desc = desc;
    }

    public Book(Long id, String author, String title, EntryType type, String desc) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.type = type;
        this.desc = desc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public EntryType getType() {
        return type;
    }

    public void setType(EntryType type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}