package com.airtribe.library.service;

import com.airtribe.library.entity.Patron;

import java.util.List;

public interface RecommendationStrategy {
    List<String> recommend(Patron patron, List<Patron> allPatrons);
}