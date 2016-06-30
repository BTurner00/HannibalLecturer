package com.theironyard.controllers;

import com.theironyard.entities.Lecturer;
import com.theironyard.entities.Review;
import com.theironyard.services.LecturerRepository;
import com.theironyard.services.ReviewRepository;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * Created by Ben on 6/30/16.
 */
@RestController
public class HannibalLecturerRestController {

    @Autowired
    LecturerRepository lecturers;
    @Autowired
    ReviewRepository reviews;


    @PostConstruct
    public void init() throws SQLException {
        Server.createWebServer().start();
    }


    @RequestMapping(path="/lecturers", method= RequestMethod.POST)
    public String postLecturers(String name, String topic, String image) {
        Lecturer lect = new Lecturer(name, topic, image);
        lecturers.save(lect);
        return "redirect:/";
    }

    @RequestMapping(path="/lecturers", method=RequestMethod.GET)
    public Iterable<Lecturer> getLecturers() {
        Iterable<Lecturer> lects = lecturers.findAll();
        return lects;
    }

    @RequestMapping(path="/reviews", method= RequestMethod.POST)
    public String postReviews(String author, String text, int lecturerId, Boolean isGood) {
        Lecturer lecturer = lecturers.findOne(lecturerId);
        Review review = new Review(author, text, isGood, lecturer);
        reviews.save(review);
        return "redirect:/";
    }

    @RequestMapping(path="/reviews", method=RequestMethod.GET)
    public Iterable<Review> getReviews(int lecturerId) {
        Iterable<Review> revs = reviews.findByLecturerIdOrderByAuthorAsc(lecturerId);
        return revs;
    }

}
