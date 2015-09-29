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
 *
 * @author Marthin
 */
public class Book implements Comparable<Book>, Serializable {

    private String ISBN,title;
    private int edition;
    private double price;
    private ArrayList<Author> authors;

    public Book(String ISBN, String title, int edition, double price) {
        this.ISBN = ISBN;
        this.title = title;
        this.edition = edition;
        this.price = price;
        authors = new ArrayList<>();
    }

    public void addAuthor(String name) {
        authors.add(new Author(name));
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public int getNmbAuthors() {
        return authors.size();
    }

    public String getAuthorName(int i) {
        return authors.get(i).getName();
    }

    public String getTitle() {
        return title;
    }

    public String getISBN() {
        return ISBN;
    }

    public int getEdition() {
        return edition;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public int compareTo(Book b) {
        return (int) this.price - (int) b.price;
    }

    @Override
    public String toString() {
        String books = new String();
        books += "Title: " + title + " Authors: ";
        for (int i = 0; i < authors.size(); i++) {
            books += authors.get(i).getName() + ", ";
        }
        books += "ISBN: " + ISBN + ", EDITION: " + edition + ", PRICE: $" + price + " \n";
        return books;
    }
}
