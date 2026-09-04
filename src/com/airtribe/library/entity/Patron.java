package com.airtribe.library.entity;

import java.util.ArrayList;
import java.util.List;

public class Patron {
    private String id;
    private String patronName;
    private String phone;



    private String email;
    private String patronAddress;
    private List<String> borrowingHistory;

    public Patron(String patronName, String phone, String email, String patronAddress) {
        this.patronName = patronName;
        this.phone = phone;
        this.email = email;
        this.patronAddress = patronAddress;
        this.borrowingHistory = new ArrayList<>();
    }

    public List<String> getBorrowingHistory() {
        return borrowingHistory;
    }

    public void setBorrowingHistory(List<String> borrowingHistory) {
        this.borrowingHistory = borrowingHistory;
    }

    public String getPatronName() {
        return patronName;
    }

    public void setPatronName(String patronName) {
        this.patronName = patronName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPatronAddress() {
        return patronAddress;
    }

    public void setPatronAddress(String patronAddress) {
        this.patronAddress = patronAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public void addToHistory(String isbn) {
        borrowingHistory.add(isbn);
    }

    public void receiveNotification(String message) {
        System.out.println("[NOTIFICATION TO " + patronName + "]: " + message);
    }
}
