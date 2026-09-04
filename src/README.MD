# Library Management System

A production-ready Low-Level Design (LLD) implementation of a Multi-Branch Library Management System in Java. This project demonstrates clean coding practices, adhering to SOLID principles, structural configurations, and decoupled behavioral processing patterns.

## Design Patterns Implemented

### 1. Factory Pattern (Creational)
- **Implemented Class:** `LibraryFactory`
- **Purpose:** Decouples complex entity construction logic from calling clients (`Main`). It isolates parameters seamlessly, making it simple to scale up and implement child specialisations like `EBooks` or `PremiumPatrons` moving forward.

### 2. Strategy Pattern (Behavioral)
- **Implemented Classes:** `BookSearchByTitle` (implements `BookSearchService`), `HistoryBasedRecommendation` (implements `RecommendationStrategy`)
- **Purpose:** Defines families of encapsulated execution strategies, rendering sorting, query filtering, and targeted recommendation algorithms completely swappable at execution runtime without shifting code core signatures.

### 3. Observer Pattern (Behavioral)
- **Implemented Context:** Event handling inside `LibraryBranch.returnBook()` tracking `Patron` listeners.
- **Purpose:** Forms transactional 1-to-many subscription structures. Upon inventory receipt changes, automated reservation engines calculate priority vectors and dispatch instant notifications directly to sub-listening profiles.

### 4. Singleton Pattern (Creational)
- **Implemented Class:** `CentralLibrarySystem`
- **Purpose:** Guarantees a globally accessed single administrative workspace instance. It securely coordinates data streams, updates profile records, and oversees complex tasks like transferring inventory between branches.

---

## SOLID Principles Compliance
- **Single Responsibility Principle (SRP):** Business orchestrations are clearly segregated out of basic entities (`Book`, `Patron`) and isolated inside service modules (`LibraryBranch`, `CentralLibrarySystem`).
- **Open/Closed Principle (OCP):** Introducing novel query operations requires implementing the `BookSearchService` interface without directly touching existing compiled algorithms.
- **Liskov Substitution Principle (LSP):** Concrete algorithmic components conform neatly to underlying abstract definitions without altering application flow state.
- **Interface Segregation Principle (ISP):** Action profiles are isolated down into fine, clean modules (`BookSearchService`), keeping dependencies lean.
- **Dependency Inversion Principle (DIP):** Top-level client runners target interfaces (`BookSearchService`) directly rather than anchoring dependencies on fixed internal classes.

---

## Class Architecture Relationship Diagram

```text
+--------------------------+          1 .. *          +-----------------------+

|   CentralLibrarySystem   |------------------------->|     LibraryBranch     |
+--------------------------+                          +-----------------------+

             |                                                    |
             | 1 .. *                                             | 1 .. *
             v                                                    v
   +--------------------+                               +--------------------+

   |       Patron       |                               |        Book        |
   +--------------------+                               +--------------------+

             |                                                    |
             | references history (ISBN Strings)                  | tracked by status
             v                                                    v
+--------------------------+                            +-----------------------+

| RecommendationStrategy   |                            |      BookStatus       |
+--------------------------+                            +-----------------------+
```

---

## Local Compilation & Execution Lines

Ensure you have **JDK 11** (or above) installed locally.

1. **Compile all modules via Terminal:**
   ```bash
   javac -d out src/com/airtribe/library/**/*.java Main.java
   ```

2. **Execute the Application Engine:**
   ```bash
   java -cp out Main
   ```
