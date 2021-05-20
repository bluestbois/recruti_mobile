/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.util.Date;



/**
 *
 * @author asus
 */
public class Job {
    private int id;
    private String title;
    private String description;
    private String date;
    private int recruiter_id;

    public Job() {
    }
    
    

    public Job(int id, String title, String description, String date, int recruiter_id) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.recruiter_id = recruiter_id;
    }

    public Job(String title, String description, String date, int recruiter_id) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.recruiter_id = recruiter_id;
    }

    public Job(String toString, String toString0, String format) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getRecruiter_id() {
        return recruiter_id;
    }

    public void setRecruiter_id(int recruiter_id) {
        this.recruiter_id = recruiter_id;
    }

    
    
    
 
    
    
   
    
    
    

   
    
}
