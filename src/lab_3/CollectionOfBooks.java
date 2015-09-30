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
 *Represents a library of books
 * 
 * @author Marthin
 * @author Rasmus
 */
public class CollectionOfBooks {
    /**
     * The library of all books
     */
    private ArrayList<Book> library;
    /**
     * Creates a library of books.
     * the library is then filled with books from a file
     */
    public CollectionOfBooks() throws Exception {
        library = new ArrayList<>();
        readFromFile();
    }
    /**
     * Adds a book to the library
     * @param book this is a Book object
     */
    public void addBook(Book book) {
        library.add(book);
    }
    /**
     * adds a book to the library.
     * @param title This is the title of the book.
     * @param authors This is a list of authors that wrote the book.
     *                A book can have more than one author.
     * @param edition This is the edition of the book.
     * @param ISBN This is the ISBN of the book.
     * @param price This is the price of the book.
     */
    public void addBook(String title, ArrayList<String> authors, int edition, String ISBN, double price) {
        Book book = new Book(ISBN, title, edition, price);
        for (String author : authors) {
            book.addAuthor(author);
        }
        addBook(book);
    }
    /**
     * Removes the specified book.
     * @param book this is the book object that will be removed.
     */
    public void removeBook(Book book) {
        library.remove(book);
    }
    /**
     * gets the number of books i the library.
     * @return the library size.
     */
    public int getNoOfBooks() {
        return library.size();
    }
    /**
     * Gets a list of books by the given title, will sort by title before it is returned
     * @param title This is the title of the book(s) that will be returned.
     *              The title does not need to be the whole title of the book,
     *              the method will check if any title contains the given sequence.
     * @return returns a list of books that contains the given title.
     */
    public ArrayList<Book> getBooksByTitle(String title) {
        ArrayList<Book> tmp = new ArrayList<>();
        String bTitle;
        for (Book book : library) {
            bTitle = book.getTitle();
            if (bTitle.toLowerCase(Locale.getDefault()).contains(title.toLowerCase(Locale.getDefault()))) {
                tmp.add(book);
            }
        }
        Collections.sort(tmp, new TitleComparator());
        return tmp;

    }
    /**
     * Gets a list of books by the given ISBN, will sort by ISBN before it is returned
     * @param ISBN This is the ISBN of the book that will be returned.
     *              The ISBN needs to matchs the ISBN of a book for it to be returned.
     * @return returns a list of books that contains the given ISBN.
     */
    public ArrayList<Book> getBooksByISBN(String ISBN) {
        ArrayList<Book> tmp = new ArrayList<>();
        String bISBN;
        for (Book book : library) {
            bISBN = book.getISBN();
            if (bISBN.equals(ISBN)) {
                tmp.add(book);
            }
        }
        Collections.sort(tmp, new ISBNComparator());
        return tmp;
    }
    /**
     * Gets a list of books by the given author, will sort by number of authors before it is returned
     * @param author This is the author of the book(s) that will be returned.
     *              The author doesn't need to be the whole name of an authors
     *              the method will check if any author contains the given sequence.
     * @return returns a list of books that contains the given author.
     */
    public ArrayList<Book> getBooksByAuthor(String author) {
        ArrayList<Book> tmp = new ArrayList<>();
        String bAuthor;
        for (Book book : library) {
            for (int i = 0; i < book.getAuthors().size(); i++) {
                bAuthor = book.getAuthors().get(i).getName();
                if (bAuthor.toLowerCase(Locale.getDefault()).contains(author.toLowerCase(Locale.getDefault())) && !tmp.contains(book)) {
                    tmp.add(book);
                }
            }
        }
        Collections.sort(tmp, new AuthorComparator());
        return tmp;
    }
    /**
     * Sorts the library by the book title
     */
    public void sortByTitle() {
        Collections.sort(library, new TitleComparator());
    }
    /**
     * Sorts the library by the book ISBN
     */
    public void sortByISBN() {
        Collections.sort(library, new ISBNComparator());
    }
    /**
     * Sorts by a books number of authors
     */
    public void sortByAuthor() {
        Collections.sort(library, new AuthorComparator());
    }
    /**
     * Methed not done.
     * @return returns null
    */
    public ArrayList sortByPrice() {
        return null;
    }
    /**
     * Tries to save the current library to a file "library.ser"
     */
    public void saveToFile() throws IOException {
        ObjectOutputStream oos = null;
        try {
            FileOutputStream fout = new FileOutputStream("library.ser");
            oos = new ObjectOutputStream(fout);
            oos.writeObject(library);
        } finally {
            if (oos != null) {
                oos.close();
            }
        }
    }
    /**
     * Tries to read from "library.ser" file, if successfull will add the books to the library.
     * @throws e throws ClassNotFoundException and InvalidClassException
     */
    public void readFromFile() throws Exception {
        ObjectInputStream ois = null;
        File f = null;
        try {
            FileInputStream fin = new FileInputStream("library.ser");
            ois = new ObjectInputStream(fin);
            library = (ArrayList<Book>) ois.readObject();
        } catch (ClassNotFoundException e) {
            System.out.println("class not found");
            throw e;
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            //throw e;
        } catch (InvalidClassException e) {
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
    /**
     * adds all the books and information in the library to a string
     * @return returns a string of all books in the library
     */
    @Override
    public String toString() {
        StringBuilder books = new StringBuilder();
        for (Book book : library) {
            books.append("Title: " + book.getTitle() + " Author(s): ");
            for (int i = 0; i < book.getNoOfAuthors(); i++) {
                books.append(book.getAuthors().get(i).getName());
                if (book.getNoOfAuthors() > 1 && i < book.getNoOfAuthors() - 1) {
                    books.append(" & ");
                } else {
                    books.append(" , ");
                }
            }
            books.append("ISBN: " + book.getISBN() + ", ");
            books.append("EDITION: " + book.getEdition() + ", ");
            books.append("PRICE: $" + book.getPrice() + " \n");
        }
        return books.toString();
    }
    /**
     * adds all the books and information in the library to a string arraylist
     * @return returns the string arraylist with all information
     */
    public ArrayList libraryToString() {
        StringBuilder strBuilder;
        ArrayList<String> tmp = new ArrayList();
        for (int i = 0; i < library.size(); i++) {
            strBuilder = new StringBuilder();
            strBuilder.append("Title: " + library.get(i).getTitle() + " ");
            strBuilder.append("Author(s):");
            for (int j = 0; j < library.get(i).getNoOfAuthors(); j++) {
                strBuilder.append(" " + library.get(i).getAuthorName(j));
            }
            strBuilder.append(" ");
            strBuilder.append("ISBN: " + library.get(i).getISBN() + " ");
            strBuilder.append("Edition: " + library.get(i).getEdition() + " ");
            strBuilder.append("Price ($): " + library.get(i).getPrice() + " ");
            tmp.add(strBuilder.toString());
        }
        return tmp;
    }
}
