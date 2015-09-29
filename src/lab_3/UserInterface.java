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
        String tmp, title, isbn;
        ArrayList<String> author = new ArrayList<>();
        int edition;
        double price;
        try {
            System.out.println("Pressing enter during Author without entering something will continue");

            System.out.print("Title: ");
            title = scan.nextLine();
            if (title.length()<1){
                title=null;
            }

            while (true) {
                System.out.print("Author: ");
                tmp=scan.nextLine();
                if (tmp.length() > 0) {
                    author.add(tmp);
                }else if (author.size()<1){
                    author.add(null);
                }else{
                    break;
                }
            }

            System.out.print("Edition: ");
            if (tmp.length()<1){
                edition=Integer.parseInt("0");
            }else {
                edition = Integer.parseInt(scan.nextLine());
            }

            System.out.print("ISBN: ");
            isbn = scan.nextLine();
            if (isbn.length()<1){
                isbn=null;
            }

            System.out.print("Price: ");
            if (tmp.length()<1){
                price = Double.parseDouble("0");
            }else {
                price = Double.parseDouble(scan.nextLine());
            }

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
                return library.getBooksByISBN(input);
            default:
                return null;
        }
    }

    public void removeBook() {
        /*ArrayList<Book> theBook = getBookBy();
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
        }*/

        /*String tmp,b;
        int pos=-1;
        ArrayList<String> tmpAL=library.getBooksByTitle("#");
        System.out.println("Enter the book to be removed");
        tmp=scan.nextLine();
        for (int i=0;i<tmpAL.size();i++){
            b = tmpAL.get(i);
            if (b.contains(tmp)){
                pos=i;
            }
        }
        if (pos>-1){
            library.removeBook(pos);
            if (library.getNoOfBooks()==tmpAL.size()-1){
                System.out.println("You removed the book from the library");
            }else{
                System.out.println("There was an error during removal");
            }
        }else{
            System.out.println("The book you were trying to remove doesn't exist in our library");
        }*/
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

        /*String ans;
        ArrayList tmp,result=new ArrayList();
        System.out.print("Enter search term: ");
        ans = scan.nextLine();
        tmp=library.getBooksByTitle(ans);
        for (int i=0;i<tmp.size();i++){
            if (!result.contains(tmp.get(i))){
                result.add(tmp.get(i));
            }
        }
        tmp=library.getBooksByAuthor(ans);
        for (int i=0;i<tmp.size();i++){
            if (!result.contains(tmp.get(i))){
                result.add(tmp.get(i));
            }
        }
        tmp=library.getBooksByISBN(ans);
        for (int i=0;i<tmp.size();i++){
            if (!result.contains(tmp.get(i))){
                result.add(tmp.get(i));
            }
        }
        for (int i=0;i<result.size();i++){
            System.out.println(result.get(i));
        }*/

    }

    public void printAllBooks() {
        //System.out.println(library.toString());
        ArrayList<String> tmp = library.libraryToString();
        for (int i = 0;i<tmp.size();i++){
            System.out.println(tmp.get(i));
        }
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
