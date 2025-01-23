package org.example;

public class Student {
    private String id;
    private String[] details;

    public Student(String id, String[] details) {
        this.id = id;
        this.details = details;
    }

    public String getID() {
        return id;
    }

    public String[] getDetails() {
        return details;
    }
}