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
import com.mycompany.myapp.entities.Forum;
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class ServicePost {

    public ArrayList<Post> posts;

    public static ServicePost instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServicePost() {
        req = new ConnectionRequest();
    }

    public static ServicePost getInstance() {
        if (instance == null) {
            instance = new ServicePost();
        }
        return instance;
    }

    public ArrayList<Post> parsePosts(String jsonText) {
        try {
            posts = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Post f = new Post();

                float id = Float.parseFloat(obj.get("id").toString());
                f.setId((int) id);
                f.setTitle(obj.get("title").toString());

                f.setDescription(obj.get("description").toString());
                
               
                
                posts.add(f);
            }
        } catch (IOException ex) {

        }
        return posts;
    }

    public ArrayList<Post> getAllPosts() {
        String url = Statics.BASE_URL + "/AllPosts";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                posts = parsePosts(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return posts;
    }
}
