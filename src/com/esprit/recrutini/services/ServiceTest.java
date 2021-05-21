/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.recrutini.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.esprit.recrutini.entities.Test;
import com.esprit.recrutini.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author amine
 */
public class ServiceTest {
    
    public static ServiceTest instance = null;
    private final ConnectionRequest request;
    
    private ServiceTest() {
        request = new ConnectionRequest();
    }
    
    public static ServiceTest getInstance(){
        if(instance == null)
            instance = new ServiceTest();
        
        return instance;
    }
    
    public void addTest(Test test){
        String URL = Statics.BASE_URL 
            + "/test/mobile/new/"
            + "?title=" + test.getTitle()
            + "&description=" + test.getDescription();
        
        request.setUrl(URL);
        request.addResponseListener( r -> {
            System.out.println(new String(request.getResponseData()));
        });
        
        NetworkManager.getInstance().addToQueueAndWait(request);
    }
    
    public ArrayList<Test> getListTests(){
        String URL = Statics.BASE_URL + "/test/mobile/";
        ArrayList<Test> result = new ArrayList<Test>();
        
        request.setUrl(URL);
        request.addResponseListener( r -> {
            JSONParser parser = new JSONParser();
            CharArrayReader reader = new CharArrayReader(
                new String(request.getResponseData()).toCharArray()
            );
            
            try{
                Map<String, Object> mapTests = parser.parseJSON(reader);
                List<Map<String, Object>> listOfMaps = 
                    (List<Map<String, Object>>) mapTests.get("root");
                
                for(Map<String, Object> object : listOfMaps){
                    Test test = new Test(
                    (int) Float.parseFloat(object.get("id").toString()),
                    object.get("title").toString(),
                    object.get("description").toString()
                    );
                    
                    result.add(test);
                    System.out.println(test.toString());
                }
            }
            catch(Exception exception){
                System.err.println(exception.toString());
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(request);
        
        return result;
    }
    
    public Test getTest(int id){
        String URL = Statics.BASE_URL + "/test/mobile/" + id;
        Test result = new Test();
        
        request.setUrl(URL);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent r) {
                JSONParser parser = new JSONParser();
                CharArrayReader reader = new CharArrayReader(
                        new String(request.getResponseData()).toCharArray()
                );
                
                try{
                    Map<String, Object> mapTests = parser.parseJSON(reader);
                    List<Map<String, Object>> listOfMaps =
                            (List<Map<String, Object>>) mapTests.get("root");
                    
                    for(Map<String, Object> object : listOfMaps){
                        
                        result.setId(
                                (int) Float.parseFloat(object.get("id").toString())
                        );
                        result.setTitle(object.get("title").toString());
                        result.setDescription(object.get("description").toString());
                        
                        System.out.println(result.toString());
                    }
                }
                catch(Exception exception){
                    System.err.println(exception.toString());
                }
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(request);
        return result;
    }
    
    public void deleteTest(int id){
        String URL = Statics.BASE_URL + "/test/delete/" + id;

        request.setUrl(URL);
        request.addResponseListener( r -> {
            System.out.println(new String(request.getResponseData()));
        });
        
        NetworkManager.getInstance().addToQueueAndWait(request);
    }
    
}
