/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Forum;
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.services.ServiceForum;
import com.mycompany.myapp.services.ServicePost;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class ListPostForm extends Form {

    public ArrayList<Post> posts;
    Form current;
    static  TextField tfIdF = new TextField();

    public ListPostForm(Form previous) {
        setTitle("List forums");

        posts = ServicePost.getInstance().getAllPosts();

        for (Post obj : posts) {
            setLayout(BoxLayout.y());

            Button spTitle = new Button();
            SpanLabel sp = new SpanLabel();
            // Button Delete = new Button("D");
            // Button Modif = new Button("M");

            spTitle.setText("Title : " + obj.getTitle());

            sp.setText("Description : " + obj.getDescription());
            /* Delete.addActionListener(e
                    -> {
                System.out.println(obj.getId());
                
                ServiceForum.getInstance().deleteForum(obj.getId());
                new ListForumsForm(previous).show();
            });
            Modif.addActionListener((evt) -> {
                //ModifForumForm.tfDescriptionM.setVisible(false);
                ModifForumForm.tfIdM.setText(String.valueOf(obj.getId()));
                //ModifForumForm.tfIdM.setVisible(false);
                ModifForumForm.tfDescriptionM.setText(obj.getDescription());
                ModifForumForm.tfTitleM.setText(obj.getTitle());
                new ModifForumForm(previous).show();
            });*/

            addAll(spTitle, sp,tfIdF);
        }
        // sp.setText(new ServiceForum().getAllForums().toString());

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> current.showBack());
    }

}
