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
import com.mycompany.myapp.utils.mailing;

/**
 *
 * @author NOUSSA
 */
public class AddProjectForm extends Form{
    
    public AddProjectForm(Form previous) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("Add a new project");
        setLayout(BoxLayout.y());
        
        TextField tfName = new TextField("","titre");
                // Picker tfDateDebut = new Picker();
                // Picker tfDateFin = new Picker();
                TextField tfCategorie = new TextField("","description");
                //SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
               // String dated=format.format(tfDateDebut.getDate());
                //SimpleDateFormat format1= new SimpleDateFormat("yyyy-MM-dd");
                //String datef=format1.format(tfDateFin.getDate());
        Button btnValider = new Button("Add Project");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length()==0)||(tfCategorie.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                      Projet c= new Projet(tfName.getText(),tfCategorie.getText());
                        if( ServiceProject.getInstance().addProject(c))
                            System.out.println("ok!");
                            //Dialog.show("Success","Connection accepted",new Command("OK"));
                       // else
                           // Dialog.show("ERROR", "Server error", new Command("OK"));
                           mailing.sendEmail("ines.guizani@esprit.tn", "Project", "Congratulations, You added a new project!  ");
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfName,tfCategorie,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                
    }
    
    
}
