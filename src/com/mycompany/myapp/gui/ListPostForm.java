/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.Preferences;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.GenericListCellRenderer;
import com.mycompany.myapp.entities.Comment;
import com.mycompany.myapp.entities.Forum;
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.services.ServiceComment;
import com.mycompany.myapp.services.ServiceForum;
import com.mycompany.myapp.services.ServicePost;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class ListPostForm extends Form {

    public ArrayList<Forum> forums;
    public ArrayList<Post> posts;
   
    Form current; 
    public ArrayList<Comment> comments;
    
    int numbreNoc = 0;
    int RatingC = 0;
    float RatingP = 0;
    // static  TextField tfIdF = new TextField();
//liste des post (forum id ) 

    public ListPostForm(Form previous, Forum f) {

        setTitle("List Postes");

        setLayout(BoxLayout.y());

        TextField Title = new TextField("", "Post Title");
        TextField Description = new TextField("", "description Post");
        Button btnValider = new Button("Add Post");

        addAll(Title, Description, btnValider);

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((Title.getText().length() == 0) || (Description.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Post p = new Post(Title.getText(), Description.getText(), f.getId());
                        if (ServicePost.getInstance().addPost(p, f.getId())) {
                             ToastBar.showMessage("Add Post success ", FontImage.MATERIAL_ADD);
                            //Dialog.show("connectedd", "succed", new Command("OK"));
                            new ListPostForm(previous, f).show();
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }

            }
        });
        System.out.println("Id Forum=> " + f.getId());
        posts = ServicePost.getInstance().getPosts(f.getId());
        System.out.println(f.getPosts());

        /*for (Post p : f.getPosts()){
            post.setId(p.getId());
            post.setTitle(p.getTitle());
            post.setDescription(p.getDescription());
             System.out.println("poooostt "+ p);
            posts.add(p);
         }*/
        for (Post obj : posts) {
            comments = ServiceComment.getInstance().getComments(obj.getId());
            if (!comments.isEmpty()) {
                for (Comment obj1 : comments) {
                    numbreNoc++;
                    RatingC = RatingC + obj1.getRating();
                    System.out.println("Rating C   :" + RatingC);
                    System.out.println("nombre de comment  :" + numbreNoc);

                }
                RatingP = (float) RatingC / numbreNoc;
                numbreNoc=0;
                RatingC=0;
            comments.clear();
            }

            System.out.println("postttt=> " + f.getPosts());
            setLayout(BoxLayout.y());

            Button spTitle = new Button();
            SpanLabel spDescription = new SpanLabel();
            SpanLabel spviews = new SpanLabel();
            SpanLabel spNOC = new SpanLabel();
            SpanLabel RatingPost = new SpanLabel();

            Button Delete = new Button("D");
            Button Modif = new Button("M");

            Container box = BoxLayout.encloseXCenter(spTitle, Delete, Modif, spviews, spNOC);

            spviews.setText(Integer.toString(obj.getViews()));
            spNOC.setText(Integer.toString(obj.getNoc()));

            spTitle.setText("Title : " + obj.getTitle());

            spTitle.addActionListener(e -> {
                ServicePost.getInstance().detailPost(obj.getId());
                System.out.println("heeeere" + obj.getId());
                ServicePost.getInstance().modifPostViews(obj);
                ToastBar.showMessage("Details Post ", FontImage.MATERIAL_INFO);
                new ListeCommentForm(previous, obj, f).show();

            });

            spDescription.setText("Description : " + obj.getDescription());
            RatingPost.setText(String.valueOf(RatingP));
            Delete.addActionListener(e
                    -> {
                System.out.println(obj.getId());

                ServicePost.getInstance().deletePost(obj.getId());
                ToastBar.showMessage("Delete Post success ", FontImage.MATERIAL_DELETE_FOREVER);
                new ListPostForm(previous, f).show();
            });

            Modif.addActionListener((ActionEvent evt) -> {
                new ModifPostForm(previous, obj, f).show();

            });

            addAll(box, spDescription, RatingPost);
            RatingP = 0;
        }
        // sp.setText(new ServiceForum().getAllForums().toString());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new ListForumsForm().showBack());
    }

}
