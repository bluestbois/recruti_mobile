/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Forum;
import com.mycompany.myapp.services.ServiceForum;
import com.mycompany.myapp.services.ServicePost;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author bhk
 */
public class ListForumsForm extends Form {

    public ArrayList<Forum> forums;
    Form current;

    public ListForumsForm() {
         setTitle("Forum");
        setLayout(BoxLayout.y());

        TextField tfTitle = new TextField("", "ForumTitle");
        TextField tfDescription = new TextField("", "description Forum");
        Button btnValider = new Button("Add Forum");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfTitle.getText().length() == 0) || (tfDescription.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Forum f = new Forum(tfTitle.getText(),tfDescription.getText());
                        if (ServiceForum.getInstance().addForum(f)) {
                            Dialog.show("Success", "Connection accepted", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }

            }
        });

        addAll(tfTitle, tfDescription, btnValider);

        forums = ServiceForum.getInstance().getAllForums();
        for (Forum obj : forums) {
            setLayout(BoxLayout.y());

            Button spTitle = new Button();
            SpanLabel sp = new SpanLabel();
            Button Delete = new Button("D");
            Button Modif = new Button("M");

            spTitle.setText("Title : " + obj.getTitle());
            spTitle.addActionListener(e -> {
                ServiceForum.getInstance().detailForum(obj.getId());
                System.out.println("heeeere"+obj.getId());
                new ListPostForm(current,obj).show();
                
                        
            });
            sp.setText("Description : " + obj.getDescription());
            Delete.addActionListener(e
                    -> {
                System.out.println(obj.getId());

                ServiceForum.getInstance().deleteForum(obj.getId());
                new ListForumsForm().show();
            });
            Modif.addActionListener((evt) -> {
               new ModifForumForm(current,obj).show();

            });

            addAll(spTitle, Delete, Modif, sp);
        }
        // sp.setText(new ServiceForum().getAllForums().toString());

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> current.showBack());
    }

}
