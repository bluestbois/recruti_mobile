/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entities.Cours;
import com.mycompany.myapp.entities.Projet;
import com.mycompany.myapp.services.ServiceCours;
import com.mycompany.myapp.services.ServiceProject;
import java.util.ArrayList;

/**
 *
 * @author NOUSSA
 */
public class ListProjectForm extends Form{
    Form current;

    public ListProjectForm(Form previous) {
        setTitle("List Project");
        ArrayList<Projet> list =ServiceProject.getInstance().getAllProjects();

        SpanLabel sp = new SpanLabel();
       // sp.setText(ServiceCours.getInstance().getAllCours().toString());
        for(Projet av : list)
        {  //String s=av.getImage();
           // System.out.println(s+"-------------------------");
            String urlm="http://localhost/recrutini/76931541_862746537475073_1999362329178800128_n.png";
           // String urlm="file:///"+av.getUrl();
           // System.out.println("-------------------------"+urlm);
            Image placeholder = Image.createImage(120,90);
            EncodedImage enc= EncodedImage.createFromImage(placeholder, false);
            URLImage urlimage = URLImage.createToStorage(enc, urlm, urlm,URLImage.RESIZE_SCALE);
  
            addButton(urlimage,av);
       
        }
        add(sp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,e -> new HomeForm(current).show()); // Revenir vers l'interface précédente

    }
     private void addButton(Image img, Projet c) {
          
       int height = Display.getInstance().convertToPixels(25.5f);
       int width = Display.getInstance().convertToPixels(27);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
      // cnt.setLeadComponent(image);
         Label id = new Label("id:"+c.getId(),"NewsBottomLine");
        // Label dated = new Label("date debut:"+c.getDate_debut_cours(),"NewsBottomLine");
        // Label datef = new Label("date fin:"+c.getDate_fin_cours(),"NewsBottomLine");
        // Label nom1 = new Label("nom complet: "+c.getAuthor(),"NewsBottomLine");
         Label nom2 = new Label("title : "+c.getTitle(),"NewsBottomLine");
         Label cat = new Label("description : "+c.getDescription(),"NewsBottomLine");


           //   FontImage.setMaterialIcon(titre, FontImage.MATERIAL_TITLE);
           //   FontImage.setMaterialIcon(text, FontImage.MATERIAL_TEXT_FIELDS);
    
            Label supp=new Label("delete");  
            supp.setUIID("NewsTopLine");
            Style suppStyle = new Style(supp.getUnselectedStyle());
            suppStyle.setFgColor(0xf21f1f);
            FontImage suppImage=FontImage.createMaterial(FontImage.MATERIAL_DELETE, suppStyle);
            supp.setIcon(suppImage);
            supp.setTextPosition(RIGHT);
             supp.addPointerPressedListener(e -> {
            Dialog dig = new Dialog("delete");
            if(dig.show("delete","delete ?","Cancel","oui")){ dig.dispose();}
            else{dig.dispose();}
                 System.out.println("supp");
            if(ServiceProject.getInstance().deleteProject(c.getId()));
            }
            );
             //button update
            Label update=new Label("update");  
            update.setUIID("NewsTopLine");
            Style updateStyle = new Style(supp.getUnselectedStyle());
            suppStyle.setFgColor(0xf21f1f);
            FontImage updateImage=FontImage.createMaterial(FontImage.MATERIAL_UPDATE, updateStyle);
            update.setIcon(updateImage);
            update.setTextPosition(RIGHT);
            update.addPointerPressedListener(e5 -> {
             new UpdateProjectForm(current,c,c.getId()).show();
            }
            );
        
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                        BoxLayout.encloseX(nom2),
                        BoxLayout.encloseX(cat),

                        //BoxLayout.encloseX(dated),
                        //BoxLayout.encloseX(datef),

                        
                        BoxLayout.encloseX(supp,update)


               ));
        
       add(cnt);
      // image.addActionListener(e -> ToastBar.showMessage( FontImage.MATERIAL_INFO));
   }
    
}
