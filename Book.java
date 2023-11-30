// Zohaib Ahmadzai
// CEN-3024C
// 11/12/2023
package com.example.lmsgui;

public class Book {
    private String barcode;
    private String title;
    private String author;
    private boolean available;


    /**
     * Constructor to initialize a Book object.
     *
     * @param barcode  The barcode of the book
     * @param title    The title of the book
     * @param author   The author of the book
     */
    public Book(String barcode, String title, String author) {
        this.barcode = barcode;
        this.title = title;
        this.author = author;
        this.available = true;
    }
    /**
     * Returns barcode of the book.
     *
     * @return The barcode of the book
     */
    public String getBarcode() {
        return barcode;
    }
    /**
     * Returns title of the book.
     *
     * @return The title of the book
     */
    public String getTitle() {
        return title;
    }
    /**
     * Returns author of the book.
     *
     * @return The author of the book
     */
    public String getAuthor() {
        return author;
    }
    /**
     * Checks if the book is available.
     *
     * @return True if the book is available; otherwise, false
     */
    public boolean isAvailable() {
        return available;
    }
    /**
     * Sets the availability of the book.
     *
     * @param available The availability status of the book
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }
    /**
     * Generates a string representation of the Book object.
     *
     * @return A formatted string containing book details
     */

    @Override
    public String toString() {
        return "Barcode: " + barcode + " | Title: " + title + " | Author: " + author + " | Available: " + available;
    }
}
