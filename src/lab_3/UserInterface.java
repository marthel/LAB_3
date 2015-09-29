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
public class UserInterface {

    private Scanner scan;
    private CollectionOfBooks library;

    public UserInterface() throws Exception {
        library = new CollectionOfBooks();
        scan = new Scanner(System.in);
    }

    public void menu() throws IOException {
        String input;
        boolean quit = false;
        while (!quit) {
            //System.out.println("Add book(1), remove book(2), search for books(3), sort Books(4), print all books(5), save and quit(6)");
            printMenu();
            input = scan.nextLine();
            switch (input) {
                case "1": //Add a book
                    addBook();
                    break;
                case "2": //remove
                    removeBook();
                    break;
                case "3": //search by title,author,isbn
                    searchBook();
                    break;
                case "4"://sort books
                    sortBooks();
                    break;
                case "5"://print all books
                    printAllBooks();
                    break;
                case "6"://quit and save to file
                    library.saveToFile();
                    quit = true;
                    break;
                case "7"://quit without saving
                    quit = true;
                    break;
                default:
                    break;
            }
        }
    }

    public void testAddBook() {
        String title, isbn;
        ArrayList<String> author = new ArrayList<>();
        int edition, price;
        title = "rakkay";
        author.add("kalle");
        isbn = "12-34-567890";
        edition = 1;
        price = 100;
        library.addBook(title, author, edition, isbn, price);
        author.clear();
        title = "Rummanof";
        author.add("Antono");
        author.add("Alexi");
        isbn = "12-34-567890";
        edition = 3;
        price = 250;
        library.addBook(title, author, edition, isbn, price);
        author.clear();
        title = "Libre";
        author.add("linn");
        author.add("fredrik");
        isbn = "12-34-567890";
        edition = 2;
        price = 200;
        library.addBook(title, author, edition, isbn, price);
        author.clear();
        title = "mammy";
        author.add("rinoy");
        author.add("anton");
        author.add("kalle");
        isbn = "12-34-567890";
        edition = 5;
        price = 300;
        library.addBook(title, author, edition, isbn, price);
        author.clear();
        title = "är namn";
        author.add("örjan");
        author.add("lukas");
        isbn = "12-34-567890";
        edition = 4;
        price = 400;
        library.addBook(title, author, edition, isbn, price);
        author.clear();
    }

    public void addBook() {
        String author, title, isbn;
        ArrayList<String> authors = new ArrayList<>();
        int edition;
        double price;
        try {
            System.out.println("Pressing enter during Author without entering something will continue");

            System.out.print("Title: ");
            title = scan.nextLine();

            while (true) {
                System.out.print("Author: ");
                author = scan.nextLine();
                if (author.length() > 0) {
                    authors.add(author);
                } else {
                    break;
                }
            }

            System.out.print("Edition: ");
            edition = Integer.parseInt(scan.nextLine());

            System.out.print("ISBN: ");
            isbn = scan.nextLine();

            System.out.print("Price: ");
            price = Double.parseDouble(scan.nextLine());

            library.addBook(title, authors, edition, isbn, price);
        } catch (Exception e) {
        }
    }

    private ArrayList<Book> getBookBy() {
        String input;
        System.out.println("by title(1), by author(2), by ISBN(3)");
        input = scan.nextLine();
        switch (input) {
            case "1":
                System.out.print("enter title ");
                input = scan.nextLine();
                return library.getBooksByTitle(input);
            case "2":
                System.out.print("enter author ");
                input = scan.nextLine();
                return library.getBooksByAuthor(input);
            case "3":
                System.out.print("enter ISBN ");
                input = scan.nextLine();
                return library.getBooksByISBN(input);
            default:
                return null;
        }
    }

    public void removeBook() {
        ArrayList<Book> theBook = getBookBy();
        String input;
        System.out.println(theBook.size());
        if (theBook.size() < 1) {
            System.out.println("no books was found");
            return;
        }
        while (theBook.size() > 1) {
            System.out.println("more than one book was found please be more specific");
            theBook = getBookBy();
        }
        System.out.println("do you want to remove " + theBook.get(0).toString() + "? (y/n)");
        input = scan.nextLine();
        if (input.equals("y")) {
            library.removeBook(theBook.get(0));
        }
    }

    public void searchBook() {
        ArrayList<Book> bookList = getBookBy();
        if (bookList.size() < 1) {
            System.out.println("no books was found");
            return;
        }
        String books = new String();
        for (Book book : bookList) {
            books += book.toString();
        }
        System.out.println(books);
    }

    public void printAllBooks() {
        System.out.println(library.toString());
        /*ArrayList<String> tmp = library.libraryToString();
         for (int i = 0; i < tmp.size(); i++) {
         System.out.println(tmp.get(i));
         }*/
    }

    public void sortBooks() {
        String input;
        System.out.println("Sort by title(1), by author(2), by ISBN(3)");
        input = scan.nextLine();
        switch (input) {
            case "1":
                library.sortByTitle();
            case "2":
                library.sortByAuthor();
            case "3":
                library.sortByISBN();
                break;
        }
    }

    private void printMenu() {
        System.out.println("-----------Menu------------");
        System.out.println("1: Add a new book");
        System.out.println("2: Remove a book");
        System.out.println("3: Search for a book");
        System.out.println("4: Sort all books");
        System.out.println("5: Print all available books");
        System.out.println("6: Exit");
        System.out.println("---------------------------");
        System.out.print("Enter: ");
    }

    public static void main(String[] args) throws Exception {
        UserInterface bookMenu = new UserInterface();
        bookMenu.menu();

    }
}
