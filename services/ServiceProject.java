/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Cours;
import com.mycompany.myapp.entities.Projet;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author NOUSSA
 */
public class ServiceProject {
    
    public ArrayList<Projet> project;
    
    public static ServiceProject instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceProject() {
         req = new ConnectionRequest();
    }

    public static ServiceProject getInstance() {
        if (instance == null) {
            instance = new ServiceProject();
        }
        return instance;
    }

    public boolean addProject(Projet c) {
        String url = Statics.BASE_URL +"/project/AddProj/?"+"title="+c.getTitle()+"&"+"description="+c.getDescription();
       
        System.out.println(url);
        //System.out.println("-----------------"+c.getDate());
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK4
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Projet> parseTasks(String jsonText){
        try {
            project=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println(tasksListJson);
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            System.out.println(list);
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Projet t = new Projet();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                //t.setDescription(((int)Float.parseFloat(obj.get("status").toString())));
                t.setTitle(obj.get("title").toString());
                t.setDescription(obj.get("description").toString());
                //t.setDate(obj.get("Date").toString());
                //t.setNom_aberge_cours(obj.get("NomAbergeCours").toString());
                //t.setCategorie(obj.get("Categorie").toString());

                //Ajouter la tâche extraite de la réponse Json à la liste
                project.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return project;
    }
    
    public ArrayList<Projet> getAllProjects(){
        String url = Statics.BASE_URL+"/project/jsonAll/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                project = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return project;
    }
     public boolean deleteProject(int id){
        String url = Statics.BASE_URL+"/project/deleteProj/"+id;
        req.setUrl(url);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
            return resultOK;   
                     
    }
       public boolean updateProject(Projet c,int id) {
        //String url = Statics.BASE_URL + "/avis/update_avis/"+id+"?" +"text="+ a.getText()+ "&" + "rating="+ a.getRating()+"&" + "titre="+ a.getTitre(); ; //création de l'URL
        String url = Statics.BASE_URL +"/project/updatecours/"+id+"?" +"title="+c.getTitle()+"&"+"&"+"description="+c.getDescription();
           System.out.println(url);
        System.out.println(url);
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
}
