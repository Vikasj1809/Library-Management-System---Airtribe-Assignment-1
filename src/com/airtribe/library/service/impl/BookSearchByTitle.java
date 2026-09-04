package com.airtribe.library.service.impl;

import com.airtribe.library.entity.Book;
import com.airtribe.library.service.BookSearchService;

import java.util.List;
import java.util.stream.Collectors;

public class BookSearchByTitle implements BookSearchService {
    @Override
    public List<Book> search(List<Book> bookList, String searchKey) {
        System.out.println("Book Search by Title >>");
        return bookList.stream()
                .filter(b -> b.getTitle().equalsIgnoreCase(searchKey))
                .collect(Collectors.toList());
    }
}
