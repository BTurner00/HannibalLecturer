package com.theironyard.entities;

import javax.persistence.*;

/**
 * Created by Ben on 6/30/16.
 */
@Entity
@Table(name="reviews")
public class Review {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable=false)
    String author;
    @Column(nullable=false)
    String text;
    @Column(nullable=false)
    Boolean isGood;
    @ManyToOne
    Lecturer lecturer;

    public Review(String author, String text, Boolean isGood, Lecturer lecturer) {
        this.author = author;
        this.text = text;
        this.isGood = isGood;
        this.lecturer = lecturer;
    }

    public Review() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getGood() {
        return isGood;
    }

    public void setGood(Boolean good) {
        isGood = good;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }
}