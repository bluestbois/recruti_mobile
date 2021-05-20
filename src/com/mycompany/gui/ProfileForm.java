


package com.mycompany.gui;
import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.BaseForm;
import com.mycompany.gui.SessionManager;
import com.mycompany.services.ServiceRecruiter;
import java.io.IOException;
import java.util.Vector;

/**
 * @author Mechlaoui
 */
public class ProfileForm extends BaseForm {

    private static String i;

    public ProfileForm(Resources res) {
        super(BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        
        getContentPane().setScrollVisible(true);
        super.addSideMenu(res);

        Image img = res.getImage("cm-logo.png");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Button modiff = new Button("Modifier");
        Button picture = new Button("Photo");
        

        //Label pp = new Label(ServiceRecruiter.UrlImage(SessionManager.getImage()), "PictureWhiteBackgrond");
        
       //Image pap = res.getImage("profile-pic.jpg");
       //pp.setIcon(pap);

        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                        GridLayout.encloseIn(3,
                                FlowLayout.encloseCenter(
                                        )
                        )
                )
        ));

        String us = SessionManager.getName();
        System.out.println(us);

        TextField name = new TextField(SessionManager.getName(), "name", 20, TextField.EMAILADDR);
        name.setUIID("TextFieldBlack");
        addStringValue("Name", name);
        TextField password = new TextField(SessionManager.getPassowrd(), "password", 20, TextField.PASSWORD);
        password.setUIID("TextFieldBlack");
        addStringValue("Password", password);

        TextField email = new TextField(SessionManager.getEmail(), "email", 20, TextField.EMAILADDR);
        email.setUIID("TextFieldBlack");
        addStringValue("email", email);
        
        TextField description = new TextField(SessionManager.getDescription(), "description", 20, TextField.EMAILADDR);
        description.setUIID("TextFieldBlack");
        addStringValue("description", description);
        
        TextField address = new TextField(SessionManager.getAddress(), "address", 20, TextField.EMAILADDR);
        address.setUIID("TextFieldBlack");
        addStringValue("address", address);
        
        TextField phoneNumber = new TextField(SessionManager.getPhoneNumber(), "phoneNumber", 20, TextField.EMAILADDR);
        phoneNumber.setUIID("TextFieldBlack");
        addStringValue("phoneNumber", phoneNumber);
        
        
        
       
        
        
        picture.setUIID("Update");
        modiff.setUIID("Edit");
        addStringValue("", picture);
        addStringValue("", modiff);
        
        TextField path = new TextField("");
        
                picture.addActionListener(e -> {
            i = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
            if (i != null) {
                Image im;
                try {
                    im = Image.createImage(i);
                    im = im.scaled(res.getImage("photo-profile.jpg").getWidth(),
                            res.getImage("photo-profile.jpg").getHeight());
                    System.out.println(i);
                    path.setText(i);

                } catch (IOException ex) {
                    System.out.println("Could not load image!");
                }
            }
        });
        
        
        modiff.addActionListener((edit)-> {
                 InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDlg    = ip.showInifiniteBlocking();
            ServiceRecruiter.EditRecruiter(name.getText(), password.getText(), email.getText(), description.getText(), address.getText(),phoneNumber.getText(), path.getText());
            SessionManager.setName(name.getText());
            SessionManager.setPassowrd(password.getText());
            SessionManager.setEmail(email.getText());
            SessionManager.setImage(name.getText()+".jpg");
               SessionManager.setDescription(description.getText());
            SessionManager.setAddress(address.getText());
            SessionManager.setPhoneNumber(phoneNumber.getText());
            Dialog.show("Succès", "Modifications des coordonnées avec succès", "Ok", null);
            ipDlg.dispose();
            refreshTheme();
            
        });
        
    } 

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}