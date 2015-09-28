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
            System.out.println("Add book(1), remove book(2), search for books(3), sort Books(4), print all books(5), save and quit(6)");
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
                default:
                    break;
            }
        }
    }

    public void addBook() {
        String tmp, title;
        ArrayList<String> author = new ArrayList<>();
        int edition, isbn;
        double price;
        try {
            System.out.print("Title: ");
            title = scan.nextLine();
            while (true) {
                System.out.print("Author(s): ");
                tmp = scan.nextLine();
                if (tmp.length() <= 0) {
                    break;
                }
                author.add(tmp);
            }
            System.out.print("Edition: ");
            edition = Integer.parseInt(scan.nextLine());
            System.out.print("ISBN: ");
            isbn = Integer.parseInt(scan.nextLine());
            System.out.print("Price: ");
            price = Double.parseDouble(scan.nextLine());
            library.addBook(title, author, edition, isbn, price);
        } catch (Exception e) {
        }
    }

    public ArrayList<Book> getBookBy() {
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
                return library.getBooksByISBN(Integer.parseInt(input));
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
        } else if (theBook.size() > 1) {
            System.out.println("more than one book was found please be more specific");
            return;
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

    public static void main(String[] args) throws Exception {
        UserInterface bookMenu = new UserInterface();
        bookMenu.menu();

    }
}
