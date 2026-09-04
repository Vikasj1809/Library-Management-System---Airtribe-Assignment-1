package com.airtribe.library.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LendingProcess {
    private Patron patron;
    private List<Book> issuedBookList = new ArrayList<>();
    private Date issuedDate;
    private Date dueDate;

    public LendingProcess(Patron patron, List<Book> issuedBookList, Date dueDate, Date issuedDate) {
        this.patron = patron;
        this.issuedBookList = issuedBookList;
        this.dueDate = dueDate;
        this.issuedDate = issuedDate;
    }

    public Patron getPatron() {
        return patron;
    }

    public void setPatron(Patron patron) {
        this.patron = patron;
    }

    public List<Book> getIssuedBook() {
        return issuedBookList;
    }

    public void setIssuedBook(List<Book> issuedBookList) {
        this.issuedBookList = issuedBookList;
    }

    public Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
