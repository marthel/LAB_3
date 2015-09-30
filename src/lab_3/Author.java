/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_3;

import java.io.*;

/**
 * Author stores the name of an author and can only return the name
 *
 * @author Marthin
 * @author rasmusjansson
 */
public class Author implements Serializable {

    private String name;

    /**
     * Adds the inserted name to the book as an author.
     *
     * @param name name of the author
     */
    public Author(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the author.
     *
     * @return the author name
     */
    public String getName() {
        return name;
    }
}
