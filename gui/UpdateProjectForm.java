/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.Cours;
import com.mycompany.myapp.entities.Projet;
import com.mycompany.myapp.services.ServiceCours;
import com.mycompany.myapp.services.ServiceProject;

/**
 *
 * @author NOUSSA
 */
public class UpdateProjectForm extends Form{
        Form current;

    public UpdateProjectForm(Form previous, Projet ct,int id) {

        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("Update project");
        setLayout(BoxLayout.y());
        TextField tfName = new TextField(ct.getTitle(),"Titre",20,TextField.ANY);
        TextField tfCategorie = new TextField(ct.getDescription(),"Description",200,TextField.ANY);
        //Picker tfDateDebut = new Picker();
        //Picker tfDateFin = new Picker();

        // SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
        // SimpleDateFormat format1= new SimpleDateFormat("yyyy-MM-dd");

         //String dated=format.format(tfDateDebut.getDate());
        // String datef=format1.format(tfDateFin.getDate());

        // tfDateDebut.setDate(date);

       // TextField tfText = new TextField("", "Text");
       // TextField tfRating = new TextField("", "Rating", 20, TextField.DECIMAL);
       //tfTitre.setText(av.getText());
       //tfText.setText(av.getTitre());

      

        Button btnValider = new Button("Update projet");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length()==0)||(tfCategorie.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                     Projet c= new Projet(tfName.getText(),tfCategorie.getText());
                        if( ServiceProject.getInstance().updateProject(c,id))
                            System.out.println("ok!");
                    } catch (NumberFormatException e) {
                        //Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
       addAll(tfName,tfCategorie,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e -> new ListProjectForm(current).show()); // Revenir vers l'interface précédente
              //  btnListAvis.addActionListener(e -> new ListAvisForm(current).show());

                
    }
 



}
