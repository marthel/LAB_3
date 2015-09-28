/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_3;

import java.util.*;

/**
 *
 * @author Marthin
 */
public class AuthorComparator implements Comparator<Book> {

    @Override
    public int compare(Book a, Book b) {
        return a.getAuthors().size() < b.getAuthors().size() ? -1 : a.getAuthors().size() == b.getAuthors().size() ? 0 : 1;
    }
}
