/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.recrutini.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.esprit.recrutini.entities.Test;
import com.esprit.recrutini.services.ServiceTest;
import com.esprit.recrutini.utils.Mailing;

/**
 *
 * @author amine
 */
public class AddTestGUI extends BaseForm {
    Form current;
    private Resources theme = UIManager.initFirstTheme("/theme");
    
    public AddTestGUI(){//Resources resources){
        super("Add a test!", BoxLayout.y());
        
        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Add a test!");
        getContentPane().setScrollVisible(false);
        
        Image icon = theme.getImage("wd-logo.png");
        icon.scaledSmallerRatio(50, 50);
        Container topBar = BorderLayout.center(new Label(icon));
        topBar.add(
                BorderLayout.SOUTH, 
                new Label("Recrutini. Terbhini.", "SidemenuTagline")
        );
        topBar.setUIID("SideCommand");
        tb.addComponentToSideMenu(topBar);

        tb.addMaterialCommandToSideMenu(
                "Add a new test",
                FontImage.MATERIAL_CREATE, 
                e -> {
                    AddTestGUI test = new AddTestGUI();
                    test.show();
                }
        );
        tb.addMaterialCommandToSideMenu(
                "Display tests", 
                FontImage.MATERIAL_LIST, 
                e -> {
                    System.err.println("Tests");
                    ServiceTest.getInstance().getListTests();
                }
        );
        
        TextField title = new TextField("","Enter title!");
        title.setUIID("title");
        addStringValue("Title",title);
        
        TextField description = new TextField("","Enter description!");
        description.setUIID("description");
        addStringValue("Description",description);
        
        Button btn = new Button("Add!");
        addStringValue("", btn);
        
        btn.addActionListener(e -> {
            if(title.getText() == "" || description.getText() == ""){
                Dialog.show("Please verify your input","","Cancel","Ok");
            }
            else{
                InfiniteProgress progress = new InfiniteProgress();
                final Dialog dialoga = progress.showInfiniteBlocking();
                
                Test test = new Test(
                   -1,
                    String.valueOf(title.getText()),
                    String.valueOf(description.getText())
                );
                
                ServiceTest.getInstance().addTest(test);
                
                try {
                    Mailing.mailing("naceuramine.saddem@esprit.tn");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                
                dialoga.dispose();
            }
        });
    }
    
    private void addStringValue(String s, Component v){
        add(BorderLayout.west(new Label(s,"PaddedLabel"))
            .add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xffffee));
        
    }
}
