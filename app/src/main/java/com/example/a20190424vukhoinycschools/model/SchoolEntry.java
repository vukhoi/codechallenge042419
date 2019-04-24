package com.example.a20190424vukhoinycschools.model;

public class SchoolEntry {

    private String name;
    private String email;
    private String math;
    private String reading;
    private String writing;
    private String numTestTaker;

    public SchoolEntry(String name, String email, String math, String reading, String writing, String numTestTaker) {
        this.name = name;
        this.email = email;
        this.math = math;
        this.reading = reading;
        this.writing = writing;
        this.numTestTaker = numTestTaker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String description) {
        this.email = description;
    }

    public String getMath() {
        return math;
    }

    public void setMath(String math) {
        this.math = math;
    }

    public String getReading() {
        return reading;
    }

    public void setReading(String reading) {
        this.reading = reading;
    }

    public String getWriting() {
        return writing;
    }

    public void setWriting(String writing) {
        this.writing = writing;
    }

    public String getNumTestTaker() {
        return numTestTaker;
    }

    public void setNumTestTaker(String numTestTaker) {
        this.numTestTaker = numTestTaker;
    }

    @Override
    public String toString() {
        return "SchoolEntry{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", math='" + math + '\'' +
                ", reading='" + reading + '\'' +
                ", writing='" + writing + '\'' +
                ", numTestTaker='" + numTestTaker + '\'' +
                '}';
    }

}
