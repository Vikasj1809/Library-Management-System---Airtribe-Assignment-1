package com.airtribe.library.service.impl;

import com.airtribe.library.entity.Book;
import com.airtribe.library.entity.Patron;

public class LibraryFactory {
    // FIX: Match the argument placement to the Book constructor signature (title, author, ISBN, year)
    public static Book createBook(String title, String author, String isbn, int year) {
        return new Book(title, author, isbn, year);
    }

    public static Patron createPatron(String id, String name, String email, String address) {
        return new Patron(id, name, email, address);
    }
}
