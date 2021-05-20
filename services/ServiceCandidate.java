/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.recrutini.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import esprit.recrutini.GUI.NewsfeedForm;
import esprit.recrutini.GUI.SignUpForm;
import esprit.recrutini.gui.ProfileForm;
import esprit.recrutini.gui.SessionManagerC;
import esprit.recrutini.utils.Statics;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author yessine darmoul
 */
public class ServiceCandidate {
    public static ServiceCandidate instance = null ;
    public static boolean resultOK = true ;
    private ConnectionRequest req;
    
    public static ServiceCandidate getInstance()
    {
        if(instance == null)
            instance = new ServiceCandidate();
        return instance ;
    }
    
    public ServiceCandidate()
    {
        req = new ConnectionRequest();
    }
    
    public void signup(TextField firstname,TextField lastname,TextField email,TextField password,TextField gender,TextField nationality,TextField phonenumber,TextField address,TextField LOE,TextField experience, Resources res){
        
        String url = Statics.BASE_URL+"/CandidateApi/signup?firstname="+firstname.getText().toString()+
        "&lastname="+lastname.getText().toString()
                +"&email="+email.getText().toString()
                +"&password="+password.getText().toString()
                 +"&gender="+gender.getText().toString()
                 +"&nationality="+nationality.getText().toString()
                 +"&phonenumber="+phonenumber.getText().toString()
                 +"&address="+address.getText().toString()
                 +"&LOE="+LOE.getText().toString()
                 +"&experience="+experience.getText().toString();    
        req.setUrl(url);
        
        if(firstname.getText().equals("")&&lastname.getText().equals("")&&email.getText().equals("")&&password.getText().equals("")
                &&gender.getText().equals("")&&nationality.getText().equals("")&&phonenumber.getText().equals("")&&address.getText().equals("")
                &&LOE.getText().equals("")&&experience.getText().equals(""))
        {
            Dialog.show("Error","Please fill all the fields","OK",null);
        }
        req.addResponseListener((e)->{
        byte[]data = (byte[]) e.getMetaData();
        String responseData = new String(data);
        System.out.println("data ===>"+responseData);
        
        }
        );
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    public void signin(TextField email,TextField password,Resources res)
    {
        String url = Statics.BASE_URL+"/CandidateApi/signin?email="+email.getText().toString()+"&password="+password.getText().toString();
            req = new ConnectionRequest(url, false);
        req.setUrl(url);
        req.addResponseListener((e) -> {
          JSONParser j = new JSONParser() ;
          String json = new String(req.getResponseData()) + "";
          System.out.println("a"+json);
          try{
          
          if(json.equals("failed")){
                            System.out.println("data2 =="+json);

              Dialog.show("Authentification failed","Please check Email or Password","OK",null);
          }else{
              System.out.println("data1 =="+json);
              Map<String,Object> Candidate = j.parseJSON(new CharArrayReader(json.toCharArray()));
               //Session  Service
                float id = Float.parseFloat(Candidate.get("id").toString());
                SessionManagerC.setId((int)id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i
                
                SessionManagerC.setPassword(Candidate.get("password").toString());
                SessionManagerC.setFirstName(Candidate.get("firstName").toString());
                SessionManagerC.setEmail(Candidate.get("email").toString());
                SessionManagerC.setAddress(Candidate.get("address").toString());
                SessionManagerC.setExperience(Candidate.get("experience").toString());
                SessionManagerC.setGender(Candidate.get("gender").toString());
                SessionManagerC.setLOE(Candidate.get("LOE").toString());
                SessionManagerC.setLastName(Candidate.get("lastName").toString());
                SessionManagerC.setNationality(Candidate.get("nationality").toString());
                SessionManagerC.setPhoneNumber(Candidate.get("phoneNumber").toString());
                
                //photo 
                
                if(Candidate.get("photo") != null)
                    SessionManagerC.setImage(Candidate.get("image").toString());
                System.out.println("current user == "+SessionManagerC.getEmail());
              if(Candidate.size()>0)
                  new NewsfeedForm(res).show();
          }
        }catch(Exception ex){
            ex.printStackTrace();
        }}
        );
        NetworkManager.getInstance().addToQueueAndWait(req);
    }


    
    
public static void EditCandidate(String firstname,String lastname, String email,String gender,String nationality,String phonenumber,String address,String LOE,String experience){
        
    String url = Statics.BASE_URL+"/CandidateApi/edit?firstname="+firstname+
        "&lastname="+lastname +"&email="+email   +"&gender="+gender +"&nationality="+nationality+"&phonenumber="+phonenumber
                 +"&address="+address +"&LOE="+LOE+"&experience="+experience;   
                MultipartRequest req = new MultipartRequest();
                
                req.setUrl(url);
                req.setPost(true);
                req.addArgument("id", String.valueOf(SessionManagerC.getId()));
                req.addArgument("firstname", firstname);
                req.addArgument("lastname", lastname);
                req.addArgument("email", email);
                req.addArgument("gender", gender);
                req.addArgument("nationality", nationality);
                req.addArgument("phonenumber", phonenumber);
                req.addArgument("address", address);
                req.addArgument("LOE", LOE);
                req.addArgument("experience", experience);
                System.out.println(email);
                req.addResponseListener((response)-> {
                    
                    byte[] data = (byte[]) response.getMetaData();
                    String s = new String(data);
                    System.out.println(s);
                                       
                });
                NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
   
    
}
