/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.AjoutJobForm;
import com.mycompany.gui.ListJobForm;
import com.mycompany.gui.SessionManager;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.Map;




/**
 *
 * @author asus
 */
public class ServiceRecruiter {
       public static ServiceRecruiter instance = null ;
    
    public static boolean resultOk = true;
    String json;
    
   private ConnectionRequest req;
    
    
    public static ServiceRecruiter getInstance() {
        if(instance==null)
            instance = new ServiceRecruiter();
        return instance;
    }
    
    
    public ServiceRecruiter() {
        req = new ConnectionRequest();
    }

    //signup
    public void signup(TextField name,TextField email,TextField password,TextField confirmPassword,TextField description,TextField address,TextField phoneNumber,TextField field,Resources res ){
       
        String url = Statics.BASE_URL+"/recruiter/recruiter/signup?name="+name.getText().toString()+"&email="+email.getText()+"&password="+password.getText().toString()+"&description="+description.getText().toString()+"&address="+address.getText().toString()+"&phoneNumber="+phoneNumber.getText().toString();
        
       req.setUrl(url);
               
        if(name.getText().equals(" ") && password.getText().equals(" ")&& email.getText().equals(" ")&& description.getText().equals(" ")&& phoneNumber.getText().equals(" ")&& address.getText().equals(" ")&& description.getText().equals(" ")&& field.getText().equals(" ")){
        
        Dialog.show("Erreur","Veuillez remplir les champs","OK",null);
        }
       req.addResponseListener((e)-> {
          byte[]data = (byte[]) e.getMetaData();
          String responseData = new String(data);
          
          System.out.println("data ===>"+responseData);
       
       
       });
        
       NetworkManager.getInstance().addToQueueAndWait(req);
       
        
        }  
    
//Signin
    
    public void signin(TextField email,TextField password,Resources rs) {
       
       String url = Statics.BASE_URL+"/recruiter/recruiter/signin?email="+email.getText().toString()+"&password="+password.getText().toString();
       req = new ConnectionRequest(url, false);
       req.setUrl(url);
    
    req.addResponseListener((e) -> {
        JSONParser j = new JSONParser ();
        String json = new String(req.getResponseData()) + "";
        
        try {
        
        if(json.equals("failed")){
            Dialog.show("Echec d'authentification","email ou mot de passe invalide","OK",null);
            
        }
        else {
            System.out.println("data =="+json);
            
            Map<String,Object> recruiter = j.parseJSON(new CharArrayReader(json.toCharArray()));
            
            //add session user
                  float id = Float.parseFloat(recruiter.get("id").toString());
                SessionManager.setId((int)id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i
                
                SessionManager.setPassowrd(recruiter.get("password").toString());
                SessionManager.setName(recruiter.get("username").toString());
                SessionManager.setEmail(recruiter.get("email").toString());
                SessionManager.setDescription(recruiter.get("description").toString());
                SessionManager.setAddress(recruiter.get("address").toString());
                SessionManager.setPhoneNumber(recruiter.get("phoneNumber").toString());
                
                //photo 
                
                if(recruiter.get("image") != null)
                    SessionManager.setImage(recruiter.get("image").toString());
            
            
            System.out.println("current user ==>"+SessionManager.getEmail()+", "+SessionManager.getPassowrd());
            
            
            if(recruiter.size() > 0)
                new AjoutJobForm(rs).show();
        }
        
        
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    });
    
     NetworkManager.getInstance().addToQueueAndWait(req);
    }    

public String getPasswordByEmail(String email, Resources rs){
    
    String url = Statics.BASE_URL+"/recruiter/recruiter/getPasswordByEmail?email="+email;
    req = new ConnectionRequest(url, false);
    req.setUrl(url);
    
    req.addResponseListener ((e) -> {
      
      JSONParser j = new JSONParser();
      
       json = new String(req.getResponseData()) + "";
      
      try {
          System.out.println("data =="+json);
          
          Map<String,Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));
      }catch(Exception ex){
          ex.printStackTrace();
      }
        
        
    });
    
    NetworkManager.getInstance().addToQueueAndWait(req);


return json;
}

 public static void EditRecruiter(String name, String password, String email, String image,String description, String address, String phoneNumber){
        
    String url = Statics.BASE_URL+"/recruiter/recruiter/editUser?name="+name+"&email="+email+"&password="+password+"&description="+description+"&address="+address+"&phoneNumber="+phoneNumber;
                MultipartRequest req = new MultipartRequest();
                
                req.setUrl(url);
                req.setPost(true);
                req.addArgument("id", String.valueOf(SessionManager.getId()));
                req.addArgument("name", name);
                req.addArgument("password", password);
                req.addArgument("email", email);
                req.addArgument("description", description);
                req.addArgument("address", address);
                req.addArgument("phoneNumber", phoneNumber);
                
                System.out.println(email);
                req.addResponseListener((response)-> {
                    
                    byte[] data = (byte[]) response.getMetaData();
                    String s = new String(data);
                    System.out.println(s);
                                                   
                });
                NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
   



}



    
    

