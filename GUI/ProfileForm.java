package esprit.recrutini.gui;
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
import esprit.recrutini.services.ServiceCandidate;
import esprit.recrutini.GUI.BaseForm;
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
        tb.setTitle("Mon Profile");
        getContentPane().setScrollVisible(true);
        super.addSideMenu(res);

//        Image img = res.getImage("back-logo.png");
//        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
//            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
//        }
//        ScaleImageLabel sl = new ScaleImageLabel(img);
//        sl.setUIID("BottomPad");
//        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Button modiff = new Button("Modifier");
        Button picture = new Button("Photo");
        

        
       //Image pap = res.getImage("profile-pic.jpg");
       //pp.setIcon(pap);

//        add(LayeredLayout.encloseIn(
//                sl,
//                BorderLayout.south(
//                        GridLayout.encloseIn(3,
//                                FlowLayout.encloseCenter(
//                                        )
//                        )
//                )
//        ));

        String us = SessionManagerC.getFirstName();
        System.out.println(us);

        TextField firstname = new TextField (SessionManagerC.getFirstName());
        firstname.setUIID("TextFieldBlack");
        addStringValue("Firstname", firstname);
        
         TextField lastname = new TextField(SessionManagerC.getLastName());
        lastname.setUIID("TextFieldBlack");
        addStringValue("Lastname", lastname);
        
        

        TextField email = new TextField(SessionManagerC.getEmail(), "email", 20, TextField.EMAILADDR);
        email.setUIID("TextFieldBlack");
        addStringValue("email", email);
        
        TextField gender = new TextField(SessionManagerC.getGender());
        gender.setUIID("TextFieldBlack");
        addStringValue("Gender", gender);
        
        TextField nationality = new TextField(SessionManagerC.getNationality());
        nationality.setUIID("TextFieldBlack");
        addStringValue("Nationality", nationality);
        
        TextField phonenumber = new TextField(SessionManagerC.getPhoneNumber());
        phonenumber.setUIID("TextFieldBlack");
        addStringValue("Phonenumber", phonenumber);
        
        TextField address = new TextField(SessionManagerC.getAddress());
        address.setUIID("TextFieldBlack");
        addStringValue("Address", address);
        
        TextField LOE = new TextField(SessionManagerC.getLOE());
        LOE.setUIID("TextFieldBlack");
        addStringValue("LOE", LOE);
        
        TextField experience = new TextField(SessionManagerC.getExperience());
        experience.setUIID("TextFieldBlack");
        addStringValue("Experience", experience);
        
        
        
       
        
        
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
            ServiceCandidate.EditCandidate(firstname.getText(),lastname.getText(), email.getText(),gender.getText(),nationality.getText(),phonenumber.getText(),address.getText(),LOE.getText(),experience.getText());
            SessionManagerC.setFirstName(firstname.getText());
            SessionManagerC.setEmail(email.getText());
            SessionManagerC.setImage(firstname.getText()+".jpg");
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