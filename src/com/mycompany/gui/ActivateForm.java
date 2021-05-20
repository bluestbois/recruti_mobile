

package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.SpanLabel;


import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.services.ServiceRecruiter;
import com.sun.mail.smtp.SMTPSSLTransport;
import com.sun.mail.smtp.SMTPTransport;

import java.util.Date;
import java.util.Properties;


import javax.mail.Message;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Account activation UI
 *
 * @author Shai Almog
 */
public class ActivateForm extends BaseForm {
 TextField email;
    public ActivateForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("IMGLogin");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("Activate");
        
        add(BorderLayout.NORTH, 
                BoxLayout.encloseY(
                        new Label(res.getImage("oublier.png"), "LogoLabel"),
                        new Label("Awsome Thanks!", "LogoLabel")
                )
        );
        
         email = new TextField("","saisir votre email",20,TextField.ANY);
        email.setSingleLineTextArea(false);
        
        Button valider = new Button("Valider");
        Label haveAnAccount = new Label ("Retour");
        Button signIn = new Button("Changer votre mot de passe");
        signIn.addActionListener ( e -> previous.showBack());
        signIn.setUIID("CenterLink");
        
        Container content = BoxLayout.encloseY(
        
                new FloatingHint(email),
                createLineSeparator(),
                valider,
                FlowLayout.encloseCenter(haveAnAccount),
                signIn
        );
        
        content.setScrollableY(true);
        
        add(BorderLayout.CENTER,content);
        
        valider.requestFocus();
        
        valider.addActionListener ( e -> {
            
           InfiniteProgress ip = new  InfiniteProgress();
           final Dialog ipDialog = ip.showInfiniteBlocking();
        
           sendMail(res);
           ipDialog.dispose();
           Dialog.show("Mot de passe","Code envoyee avec succes",new Command("OK"));
        new SignInForm(res).show();
        refreshTheme();
        });
        
    }
    
     public void sendMail (Resources res){
         try {
             
         Properties props = new Properties();

              props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.required", "true");

                
                Session session = Session.getInstance(props, null);
                MimeMessage msg = new MimeMessage(session);
                msg.setFrom (new InternetAddress(" Recrutini platform <nomEmail@domaine.com>"));
                msg.setRecipients(Message.RecipientType.TO,email.getText().toString());
                msg.setSubject("Forget Password");
                msg.setSentDate(new Date(System.currentTimeMillis()));
                String mp=ServiceRecruiter.getInstance().getPasswordByEmail(email.getText().toString(), res);
                String txt = "Welcome to RECRUTINI : Your password :"+mp+"   ";
                msg.setText(txt);
                
                SMTPTransport st = (SMTPSSLTransport)session.getTransport("smtps");
                
                st.connect("smtp.gmail.com",465,"recruritini@gmail.com","recrutinirecrutini");
                
                st.sendMessage(msg, msg.getAllRecipients());
                System.out.println("server response : "+st.getLastServerResponse());
                
                      
             

             
         }catch(Exception e){
             e.printStackTrace();
     }
}

    
    
}
