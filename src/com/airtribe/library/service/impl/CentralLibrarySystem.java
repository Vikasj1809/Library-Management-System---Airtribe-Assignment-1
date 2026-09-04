package com.airtribe.library.service.impl;

import com.airtribe.library.constant.BookStatus;
import com.airtribe.library.entity.Book;
import com.airtribe.library.entity.Patron;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class CentralLibrarySystem {
    private static final Logger logger = Logger.getLogger(CentralLibrarySystem.class.getName());
    private static CentralLibrarySystem instance;

    private final Map<String, LibraryBranch> branches = new HashMap<>();
    private final Map<String, Patron> patrons = new HashMap<>();

    private CentralLibrarySystem() {}

    public static synchronized CentralLibrarySystem getInstance() {
        if (instance == null) {
            instance = new CentralLibrarySystem();
        }
        return instance;
    }

    public void addBranch(LibraryBranch branch) {
        branches.put(branch.getBranchId(), branch);
    }

    public void addPatron(Patron patron) {
        patrons.put(patron.getId(), patron);
    }

    public List<Patron> getAllPatrons() {
        return new ArrayList<>(patrons.values());
    }

    public synchronized void transferBook(String isbn, String fromBranchId, String toBranchId) {
        LibraryBranch fromBranch = branches.get(fromBranchId);
        LibraryBranch toBranch = branches.get(toBranchId);

        if (fromBranch != null && toBranch != null) {
            Book book = fromBranch.getBooks().get(isbn);
            if (book != null && fromBranch.getBookStatus(isbn) == BookStatus.AVAILABLE) {
                fromBranch.removeBook(isbn);
                toBranch.addBook(book);
                logger.info("Transferred book: " + book.getTitle() + " from branch " + fromBranchId + " to " + toBranchId);
            } else {
                logger.severe("Transfer failed. Book not available or branch mismatch.");
            }
        }
    }
}
