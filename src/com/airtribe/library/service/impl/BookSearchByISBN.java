package com.airtribe.library.service.impl;

import com.airtribe.library.entity.Book;
import com.airtribe.library.service.BookSearchService;

import java.util.List;
import java.util.stream.Collectors;

public class BookSearchByISBN implements BookSearchService {
    @Override
    public List<Book> search(List<Book> bookList, String searchKey) {
        System.out.println("Book Search by ISBN >>");
        return bookList.stream()
                .filter(b -> b.getISBN().equalsIgnoreCase(searchKey))
                .collect(Collectors.toList());
    }
}
