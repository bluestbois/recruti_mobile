
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;

import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Job;
import com.mycompany.utils.Statics;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author asus
 */
public class ServiceJob {
    
    public static ServiceJob instance = null ;
    
    public static boolean resultOk = true;
    
    
   private ConnectionRequest req;
    
    
    public static ServiceJob getInstance() {
        if(instance==null)
            instance = new ServiceJob();
        return instance;
    }
    
    
    public ServiceJob() {
        req = new ConnectionRequest();
        
    }
    //ajout
    public void ajoutJob(Job job){
       
        String url =Statics.BASE_URL+"/job/addJobJson/new?title="+job.getTitle()+"&description="+job.getDescription()+"&recruiter_id="+job.getRecruiter_id();
        
        req.setUrl(url);
        req.addResponseListener ((e) -> {
        
            String str = new String(req.getResponseData());
       System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
    
    //Afichage
    public ArrayList<Job>affichageJobs() {
        
        ArrayList<Job> result = new ArrayList<>();
       String url =Statics.BASE_URL+"/job/displayJobs";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt){
                   JSONParser jsonp ;
                   jsonp = new JSONParser();
                   
                   try{
                       Map<String,Object>mapJobs = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                   List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>) mapJobs.get("root");
                   
                   for(Map<String, Object> obj : listOfMaps){
                       Job j = new Job ();
                       
                       float id = Float.parseFloat(obj.get("id").toString());
                       
                       String title = obj.get("title").toString();
                       String description = obj.get("description").toString();
                       //String recruiter_id = obj.get("recruiter_id").toString();
                       
                       j.setId((int) id);
                       j.setTitle(title);
                       j.setDescription(description);
                       //j.setRecruiter_id(recruiter_id);
                       
                      //String DateConverter = obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp")+ 10 , obj.get("date").toString().lastIndexOf("}"));
                    
                       //Date currentTime = new Date(Double.valueOf (DateConverter).longValue() * 1000);
                       
                       //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                       //String dateString = formatter.format(currentTime);
                       //j.setDate(dateString);
                   
                       result.add(j);
                   }
                   
                   }catch(Exception ex){
                       ex.printStackTrace();
                   }
                }
                
                });
        
         NetworkManager.getInstance().addToQueueAndWait(req);
      return result;
    }


//delete
    public boolean deleteJob(int id) {
        String url = Statics.BASE_URL +"/job/deleteJobJSON/"+id;
      
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>()  {
           @Override
           public void actionPerformed(NetworkEvent evt) {
               
              req.removeResponseCodeListener(this);
           }
        
                });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOk;
            
    
    }



}