package com.andrestorres.oneup.models.utils;

/**
 * Created by andrestorres on 12/15/16.
 */

public class Commit {
    private Author author;
    private  String message;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
