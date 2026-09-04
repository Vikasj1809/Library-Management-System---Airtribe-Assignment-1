package com.airtribe.library.entity;

import java.util.ArrayList;
import java.util.List;

public class InventoryManagement {
    private List<Book> availableBookList = new ArrayList<>();
    private List<Book> borrowedBookList = new ArrayList<>();

    public List<Book> getAvailableBookList() {
        return availableBookList;
    }

    public void setAvailableBookList(List<Book> availableBookList) {
        this.availableBookList = availableBookList;
    }

    public List<Book> getBorrowedBookList() {
        return borrowedBookList;
    }

    public void setBorrowedBookList(List<Book> borrowedBookList) {
        this.borrowedBookList = borrowedBookList;
    }
}
