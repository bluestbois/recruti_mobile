/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;
import java.util.Objects;
/**
 *
 * @author ines
 */
public class Projet {

    private int id;
    private int recruiter_id;
    private String title;
    private String description;
    
    
    public Projet(int id, int recruiter_id, String title, String description) {
        this.id = id;
        this.recruiter_id = recruiter_id;
        this.title = title;
        this.description = description;
        
        
    }

    public Projet() {
    }

    public Projet(String title, String description) {
        this.title = title;
        this.description = description;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRecruiter_id() {
        return recruiter_id;
    }

    public void setRecruiter_id(int recruiter_id) {
        this.recruiter_id = recruiter_id;
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

  /*  public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
*/

}
