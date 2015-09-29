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

    private ArrayList<Book> library;

    public CollectionOfBooks() throws Exception {
        library = new ArrayList<>();
        readFromFile();
    }

    public void addBook(Book book) {
        library.add(book);
    }

    public void addBook(String title, ArrayList<String> authors, int edition, String ISBN, double price) {
        Book book = new Book(ISBN, title, edition, price);
        for (String author : authors) {
            book.addAuthor(author);
        }
        addBook(book);
    }

    public void removeBook(Book book) {
        library.remove(book);
    }

    public int getNoOfBooks() {
        return library.size();
    }

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

    public void sortByTitle() {
        Collections.sort(library, new TitleComparator());
    }

    public void sortByISBN() {
        Collections.sort(library, new ISBNComparator());
    }

    public void sortByAuthor() {
        Collections.sort(library, new AuthorComparator());
    }

    public ArrayList sortByPrice() {
        return null;
    }

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

    @Override
    public String toString() {
        StringBuilder books = new StringBuilder();
        for (Book book : library) {
            books.append("Title: " + book.getTitle() + " Author(s): ");
            for (int i = 0; i < book.getNoOfAuthors(); i++) {
                books.append(book.getAuthors().get(i).getName());
                if(book.getNoOfAuthors()>1 && i < book.getNoOfAuthors()-1) {
                    books.append(" & ");
                }
                else {
                    books.append(" , ");
                }
            }
            books.append("ISBN: " + book.getISBN() + ", ");
            books.append("EDITION: " + book.getEdition() + ", ");
            books.append("PRICE: $" + book.getPrice() + " \n");
        }
        return books.toString();
    }
    public ArrayList libraryToString(){
        StringBuilder strBuilder;
        ArrayList<String> tmp = new ArrayList();
        for (int i =0;i<library.size();i++) {
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
