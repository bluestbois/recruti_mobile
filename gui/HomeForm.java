/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form {

    Form current;
    /*Garder traçe de la Form en cours pour la passer en paramètres 
    aux interfaces suivantes pour pouvoir y revenir plus tard en utilisant
    la méthode showBack*/
    
    public HomeForm(Form previous) {
        current = this; //Récupération de l'interface(Form) en cours
        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
        
        Button btnAddCours = new Button("Add Cours");
        Button btnListCours = new Button("List Cours");
        Button btnListProjects = new Button("List Projects");
        Button btnAddProjects = new Button("Add Project");

        
        btnAddCours.addActionListener(e -> new AddCoursForm(current).show());
        btnListCours.addActionListener(e -> new ListCoursForm(current).show());
        
        btnListProjects.addActionListener(e -> new ListProjectForm(current).show());
        btnAddProjects.addActionListener(e -> new AddProjectForm(current).show());
        //btnListTasks.addActionListener(e -> new ListCoursForm(current).show());
        addAll(btnListProjects,btnAddProjects);

    }

}
