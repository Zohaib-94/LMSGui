// Zohaib Ahmadzai
// CEN-3024C
// 11/12/2023
package com.example.lmsgui;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;



public class Library {
    private List<Book> books = new ArrayList<>();
    /**
     * Adds a book to the library.
     *
     * @param book The book to be added
     */
    public void addBook(Book book) {
        books.add(book);
    }
    /**
     * Retrieves a list of all books in the library.
     *
     * @return The list of books in the library
     */

    public List<Book> getBooks() {
        return books;
    }
    /**
     * Removes a book from the library based on its title.
     *
     * @param title The title of the book to be removed
     * @return True if the book is successfully removed, false otherwise
     */

    public boolean removeBookByTitle(String title) {

        return false;
    }

    /**
     * Loads books from the database and populates the library.
     */

    public void loadBooksFromDatabase() {

        String url = "jdbc:sqlite:C:\\sqlite\\sqlite.db";
        String query = "SELECT barcode, title, author, available FROM books";

        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String barcode = resultSet.getString("barcode");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                boolean available = resultSet.getBoolean("available");

                Book book = new Book(barcode, title, author);
                book.setAvailable(available);
                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Checks out a book from the library based on its title.
     *
     * @param title The title of the book to be checked out
     * @return True if the book is successfully checked out, false otherwise
     */
    public boolean checkOutBookByTitle(String title) {
        return false;
    }
    /**
     * Checks in a book to the library based on its title.
     *
     * @param title The title of the book to be checked in
     * @return True if the book is successfully checked in, false otherwise
     */
    public boolean checkInBook(String title) {
        return false;
    }
    /**
     * Represents a Book with its attributes: barcode, title, author, and availability status.
     */
    public static class Book {
        private String barcode;
        private String title;
        private String author;
        private boolean available;

        public Book(String barcode, String title, String author) {
            this.barcode = barcode;
            this.title = title;
            this.author = author;
            this.available = true;
        }

        public String getBarcode() {
            return barcode;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }
    }
}


