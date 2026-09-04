package com.airtribe.library.service;

import com.airtribe.library.entity.Book;

import java.util.List;

public interface BookSearchService {
    List<Book> search(List<Book> bookList, String searchKey);
}
