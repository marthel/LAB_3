/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_3;

import java.io.*;
import java.util.*;
import java.lang.*;

/**
 *
 * @author Marthin
 */
public class CollectionOfBooks {

    private ArrayList<Book> theBooks;

    public CollectionOfBooks() throws Exception {
        theBooks = new ArrayList<>();
        readFromFile();
    }

    public void addBook(Book book) {
        theBooks.add(book);
    }

    public void addBook(String title, ArrayList<String> authors, int edition, int ISBN, double price) {
        Book book = new Book(ISBN, title, edition, price);
        for (String author : authors) {
            book.addAuthor(author);
        }
        addBook(book);
    }

    public void removeBook(Book book) {
        theBooks.remove(book);
    }

    public int getNoOfBooks() {
        return theBooks.size();
    }

    public ArrayList<Book> getBooksByTitle(String title) {
        ArrayList<Book> tmp = new ArrayList<>();
        String bTitle;
        for (Book book : theBooks) {
            bTitle = book.getTitle();
            if (bTitle.toLowerCase().contains(title.toLowerCase())) {
                tmp.add(book);
            }
        }
        Collections.sort(tmp, new TitleComparator());
        return tmp;

    }

    public ArrayList<Book> getBooksByISBN(int ISBN) {
        ArrayList<Book> tmp = new ArrayList<>();
        int bISBN;
        for (Book book : theBooks) {
            bISBN = book.getISBN();
            if (bISBN == ISBN) {
                tmp.add(book);
            }
        }
        Collections.sort(tmp, new ISBNComparator());
        return tmp;
    }

    public ArrayList<Book> getBooksByAuthor(String author) {
        ArrayList<Book> tmp = new ArrayList<>();
        String bAuthor;
        for (Book book : theBooks) {
            for (int i = 0; i < book.getAuthors().size(); i++) {
                bAuthor = book.getAuthors().get(i).getName();
                if (bAuthor.toLowerCase().contains(author.toLowerCase()) && !tmp.contains(book)) {
                    tmp.add(book);
                }
            }
        }
        Collections.sort(tmp, new AuthorComparator());
        return tmp;
    }

    public void sortByTitle() {
        Collections.sort(theBooks, new TitleComparator());
    }

    public void sortByISBN() {
        Collections.sort(theBooks, new ISBNComparator());
    }

    public void sortByAuthor() {
        Collections.sort(theBooks, new AuthorComparator());
    }

    public ArrayList sortByPrice() {
        return null;
    }

    public void saveToFile() throws IOException {
        ObjectOutputStream oos = null;
        try {
            FileOutputStream fout = new FileOutputStream("library.ser");
            oos = new ObjectOutputStream(fout);
            oos.writeObject(theBooks);
        } finally {
            if (oos != null) {
                oos.close();
            }
        }
    }

    public void readFromFile() throws Exception {
        ObjectInputStream ois = null;
        try {
            FileInputStream fin = new FileInputStream("library.ser");
            ois = new ObjectInputStream(fin);
            theBooks = (ArrayList<Book>) ois.readObject();
        } catch (ClassNotFoundException e) {
            System.out.println("class not found");
            throw e;
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
            }
        }
    }

    @Override
    public String toString() {
        String books = new String();
        for (Book book : theBooks) {
            books += "Title: " + book.getTitle() + " Author(s): ";
            for (int i = 0; i < book.getAuthors().size(); i++) {
                books += book.getAuthors().get(i).getName() + ", ";
            }
            books += "ISBN: " + book.getISBN() + ", EDITION: " + book.getEdition() + ", PRICE: $" + book.getPrice() + " \n";
        }
        return books;
    }
}
