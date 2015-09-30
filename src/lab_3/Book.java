/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_3;

import java.io.*;
import java.lang.*;
import java.util.*;

/**
 * Handles everything that has to do with a single book. It can return every property of
 * the book and it can return all properties in a single string if needed.
 *
 * @author Marthin
 * @author rasmusjansson
 */
public class Book implements Comparable<Book>, Serializable {

    /**
     * The ISBN and Title of the book.
     */
    private String ISBN, title;

    /**
     * The Edition of the book.
     */
    private int edition;

    /**
     * The price of the book.
     */
    private double price;

    /**
     * An ArrayList<Author> which will contain the author or authors of the book.
     */
    private ArrayList<Author> authors;

    /**
     * Constructs a book with the information provided.
     *
     * @param ISBN ISBN of the book
     * @param title title of the book
     * @param edition edition of the book
     * @param price price of the book
     */
    public Book(String ISBN, String title, int edition, double price) {
        this.ISBN = ISBN;
        this.title = title;
        this.edition = edition;
        this.price = price;
        authors = new ArrayList<>();
    }

    /**
     * Adds a new author to the book.
     *
     * @param name name of the author to be added
     */
    public void addAuthor(String name) {
        authors.add(new Author(name));
    }

    /**
     * Returns an ArrayList<Author> of all the authors to this book.
     *
     * @return ArrayList<Author> of the authors of the book
     */
    public ArrayList<Author> getAuthors() {
        return authors;
    }

    /**
     * Returns how many authors there are for this book.
     *
     * @return Numbers of authors
     */
    public int getNoOfAuthors() {
        return authors.size();
    }

    /**
     * Returns the author at index.
     *
     * @param index index of which to get the name from
     * @return The name at index
     */
    public String getAuthorName(int index) {
        return authors.get(index).getName();
    }

    /**
     * Returns the Title of the book.
     *
     * @return Title of book
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns ISBN of the book.
     *
     * @return ISBN of book
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     * Returns Edition of the book.
     *
     * @return Edition of book
     */
    public int getEdition() {
        return edition;
    }

    /**
     * Returns Price of the book.
     *
     * @return Price of book
     */
    public double getPrice() {
        return price;
    }

    /**
     * Compares the price of this book and another one.
     * If this book == b, then both book cost the same
     * If this book <0 b, then b cost more
     * If this book >0 b, the this book cost more
     *
     * @param b b is the book the compared to
     * @return the difference between the books
     */
    @Override
    public int compareTo(Book b) {
        return (int) this.price - (int) b.price;
    }

    /**
     * Converts book to a String with all information then returns it.
     *
     * @return The book in String form
     */
    @Override
    public String toString() {
        String book = new String();
        book += "Title: " + title + " Authors: ";
        for (int i = 0; i < authors.size(); i++) {
            book += authors.get(i).getName() + ", ";
        }
        book += "ISBN: " + ISBN + ", EDITION: " + edition + ", PRICE: $" + price + " \n";
        return book;
    }
}
