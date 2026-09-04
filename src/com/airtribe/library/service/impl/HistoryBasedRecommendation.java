package com.airtribe.library.service.impl;

import com.airtribe.library.entity.Patron;
import com.airtribe.library.service.RecommendationStrategy;
// or whatever your interface is named
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class HistoryBasedRecommendation implements RecommendationStrategy {
    @Override
    public List<String> recommend(Patron patron, List<Patron> allPatrons) {
        if (patron == null || allPatrons == null) return List.of();

        return allPatrons.stream()
                // Null-safe check: exclude the current patron evaluating recommendations
                .filter(p -> p != null && !Objects.equals(p.getPatronName(), patron.getPatronName()))
                .flatMap(p -> p.getBorrowingHistory().stream())
                // Only suggest books that Bob hasn't borrowed yet
                .filter(isbn -> !patron.getBorrowingHistory().contains(isbn))
                .distinct()
                .limit(5)
                .collect(Collectors.toList());
    }
}
