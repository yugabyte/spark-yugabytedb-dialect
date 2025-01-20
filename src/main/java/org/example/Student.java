package org.example;

import java.util.ArrayList;

public class Student {
    private String id;
    private String[] somearray;

    public Student(String id, String[] somearray) {
        this.id = id;
        this.somearray = somearray;
    }

    public String getID() {
        return id;
    }

    public String[] getSomeArray() {
        return somearray;
    }
}