package org.library.Model;

public interface LibraryItem {
    void addItemCopies(int copies);
    boolean available();
    void decrementCopies();
    void incrementCopies();
}
