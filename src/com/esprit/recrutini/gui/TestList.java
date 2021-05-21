/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.recrutini.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import static com.codename1.ui.events.ActionEvent.Type.Command;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.esprit.recrutini.entities.Test;
import com.esprit.recrutini.services.ServiceTest;

import java.util.ArrayList;


/**
 *
 * @author zcart
 */
public class TestList extends BaseForm {

  
    Form current;
    private Resources theme = UIManager.initFirstTheme("/theme");
    
    public TestList(){
      
        super(BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setTitle("Liste des Articles");
        getContentPane().setScrollVisible(true);
        //super.addSideMenu(res);

        
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
        
        add(LayeredLayout.encloseIn(
                BorderLayout.south(
                        GridLayout.encloseIn(3,
                                FlowLayout.encloseCenter(
                                        )
                        )
                )
        ));
   
 
      

     
        
        
       
                ArrayList<Test> list = ServiceTest.getInstance().getListTests();


        {
           
            for (Test t : list) {

          

                Container c3 = new Container(BoxLayout.y());
               
                SpanLabel cat=  new SpanLabel("Title :" + t.getTitle());
                SpanLabel cat1= new SpanLabel("Description :" + t.getDescription());
                 
                c3.add(cat);
                c3.add(cat1);
                
       
                Button Delete =new Button("Delete");
                c3.add(Delete);
            Delete.getAllStyles().setBgColor(0xF36B08);
            Delete.addActionListener(e -> {
               Dialog alert = new Dialog("Attention");
                SpanLabel message = new SpanLabel("Are you sure you want to delete this item?");
                alert.add(message);
                Button ok = new Button("Yes");
                Button cancel = new Button(new Command("Cancel"));
                //User clicks on ok to delete account
                ok.addActionListener(new ActionListener() {
                  
                    public void actionPerformed(ActionEvent evt) {
                       ServiceTest.getInstance().deleteTest((int) t.getId());
                     alert.dispose();
                         ToastBar.Status status = ToastBar.getInstance().createStatus();
                    status.setShowProgressIndicator(true);
   //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                              status.setMessage("Test deleted!");
                                                  status.setExpires(10000);   
                      status.show();
                  
                     
                    
                    
              
                      
                        refreshTheme();
                    }
                    
                }
                
                
                );

                alert.add(cancel);
                alert.add(ok);
                alert.showDialog();
                
                //new ListArticle(previous).show();
              
                
             
            });
                       
                        
           System.out.println("");
              
  add(c3);
  
        
  
            
         
            }
          
        }
     
    }
    
        
        
    }