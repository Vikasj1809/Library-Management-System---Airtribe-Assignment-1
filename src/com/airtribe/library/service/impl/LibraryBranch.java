package com.airtribe.library.service.impl;

import com.airtribe.library.constant.BookStatus;
import com.airtribe.library.entity.Book;
import com.airtribe.library.entity.Patron;
import com.airtribe.library.service.BookSearchService;

import java.util.*;
import java.util.logging.Logger;

public class LibraryBranch {
    private static final Logger logger = Logger.getLogger(LibraryBranch.class.getName());
    
    private final String branchId;
    private final String branchName;
    private final Map<String, Book> books = new HashMap<>();
    private final Map<String, BookStatus> inventory = new HashMap<>();
    private final Map<String, Queue<Patron>> reservations = new HashMap<>();

    public LibraryBranch(String branchId, String branchName) {
        this.branchId = branchId;
        this.branchName = branchName;
    }

    public String getBranchId() { return branchId; }
    public String getBranchName() { return branchName; }

    public synchronized void addBook(Book book) {
        books.put(book.getISBN(), book);
        inventory.put(book.getISBN(), BookStatus.AVAILABLE);
        logger.info("Added book: " + book.getTitle() + " to branch " + branchName);
    }

    public synchronized void removeBook(String isbn) {
        books.remove(isbn);
        inventory.remove(isbn);
        reservations.remove(isbn);
        logger.info("Removed book with ISBN: " + isbn + " from branch " + branchName);
    }

    public List<Book> searchBooks(BookSearchService bookSearchService, String query) {
        return bookSearchService.search(new ArrayList<>(books.values()), query);
    }

    public synchronized boolean checkoutBook(String isbn, Patron patron) {
        if (inventory.get(isbn) == BookStatus.AVAILABLE) {
            inventory.put(isbn, BookStatus.BORROWED);
            patron.addToHistory(isbn);
            logger.info("Checked out " + isbn + " to patron " + patron.getPatronName());
            return true;
        }
        logger.warning("Checkout failed for ISBN: " + isbn + ". Book status: " + inventory.get(isbn));
        return false;
    }

    public synchronized void returnBook(String isbn) {
        if (reservations.containsKey(isbn) && !reservations.get(isbn).isEmpty()) {
            inventory.put(isbn, BookStatus.RESERVED);
            Patron nextPatron = reservations.get(isbn).poll();
            if (nextPatron != null) {
                nextPatron.receiveNotification("The reserved book " + isbn + " is now available for pickup.");
            }
        } else {
            inventory.put(isbn, BookStatus.AVAILABLE);
        }
        logger.info("Returned book with ISBN: " + isbn);
    }

    public synchronized void reserveBook(String isbn, Patron patron) {
        reservations.computeIfAbsent(isbn, k -> new LinkedList<>()).add(patron);
        if (inventory.get(isbn) == BookStatus.AVAILABLE) {
            inventory.put(isbn, BookStatus.RESERVED);
        }
        logger.info("Patron " + patron.getPatronName() + " reserved book " + isbn);
    }

    public Map<String, Book> getBooks() { return books; }
    public BookStatus getBookStatus(String isbn) { return inventory.getOrDefault(isbn, null); }
}
