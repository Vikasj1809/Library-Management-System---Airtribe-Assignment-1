import com.airtribe.library.entity.Book;
import com.airtribe.library.entity.Patron;
import com.airtribe.library.service.impl.*;

import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        CentralLibrarySystem system = CentralLibrarySystem.getInstance();

        // 1. Setup Branches
        LibraryBranch downtownBranch = new LibraryBranch("B1", "Downtown Branch");
        LibraryBranch uptownBranch = new LibraryBranch("B2", "Uptown Branch");
        system.addBranch(downtownBranch);
        system.addBranch(uptownBranch);

        // 2. Populate Inventory (FIXED PARAMETER ORDER: ISBN first, then Title)
        // Pass TITLE first, then AUTHOR, then ISBN, then YEAR
        Book book1 = LibraryFactory.createBook("Effective Java", "Joshua Bloch", "978-0134685991", 2018);
        Book book2 = LibraryFactory.createBook("Clean Code", "Robert C. Martin", "978-0132350884", 2008);

        downtownBranch.addBook(book1);
        downtownBranch.addBook(book2);
        Thread.sleep(50); // Small pause to allow logger threads to sync in console

        System.out.println("\n--- SCENARIO 1: SEARCH FUNCTIONALITY ---");
        List<Book> searchResult = downtownBranch.searchBooks(new BookSearchByTitle(), "Clean Code");
        if (!searchResult.isEmpty()) {
            System.out.println("[SUCCESS] Search Found Book: " + searchResult.get(0).getTitle() + " by " + searchResult.get(0).getAuthor());
        } else {
            System.out.println("[FAILED] No books found matching the search criteria.");
        }

        // 3. Setup Patrons & Borrowing Profiles
        Patron alice = LibraryFactory.createPatron("P1", "Alice Smith", "alice@example.com", "NY");
        Patron bob = LibraryFactory.createPatron("P2", "Bob Jones", "bob@example.com","TN");
        system.addPatron(alice);
        system.addPatron(bob);

        // Seed Alice's history so Bob can get recommendations later
        alice.addToHistory("978-0132350884"); 

        System.out.println("\n--- SCENARIO 2: LENDING & RESERVATION PIPELINE ---");
        // Alice checks out Effective Java successfully
        downtownBranch.checkoutBook("978-0134685991", alice);
        Thread.sleep(50);

        // Bob tries to reserve it because it's now BORROWED
        System.out.println("Current Book Status: " + downtownBranch.getBookStatus("978-0134685991"));
        downtownBranch.reserveBook("978-0134685991", bob);
        Thread.sleep(50);

        // Alice returns it -> Automatically triggers notification callback to Bob
        downtownBranch.returnBook("978-0134685991");
        Thread.sleep(50);

        System.out.println("\n--- SCENARIO 3: INTER-BRANCH TRANSFER ---");
        // Transfer 'Clean Code' from Downtown (B1) to Uptown (B2)
        system.transferBook("978-0132350884", "B1", "B2");
        Thread.sleep(50);
        
        // Verify destination branch received it
        System.out.println("Uptown Inventory Status for Clean Code: " + uptownBranch.getBookStatus("978-0132350884"));

        System.out.println("\n--- SCENARIO 4: DATA-DRIVEN RECOMMENDATIONS ---");
        HistoryBasedRecommendation recommender = new HistoryBasedRecommendation();
        List<String> recommendations = recommender.recommend(bob, system.getAllPatrons());
        System.out.println("Recommendations for Bob based on network history: " + recommendations);
    }
}
