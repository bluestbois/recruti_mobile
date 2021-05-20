/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.Preferences;

/**
 *
 * @author asus
 */
public class SessionManager {
    public static Preferences pref ; // 3ibara memoire sghira nsajlo fiha data 
    
    
    
    // hethom données ta3 user lyt7b tsajlhom fi session  ba3d login 
    private static int id ; 
    private static String name ; 
    private static String email; 
    private static String passowrd ;
    private static String image;
     private static String description;
      private static String address;
       private static String phoneNumber;
    

    public static Preferences getPref() {
        return pref;
    }

    public static void setPref(Preferences pref) {
        SessionManager.pref = pref;
    }

    public static int getId() {
        return pref.get("id",id);// kif nheb njib id user connecté apres njibha men pref 
    }

    public static void setId(int id) {
        pref.set("id",id);//nsajl id user connecté  w na3tiha identifiant "id";
    }

    public static String getName() {
        return pref.get("username",name);
    }

    public static void setName(String userName) {
         pref.set("username",name);
    }

    public static String getEmail() {
        return pref.get("email",email);
    }

    public static void setEmail(String email) {
         pref.set("email",email);
    }

    public static String getPassowrd() {
        return pref.get("passowrd",passowrd);
    }

    public static void setPassowrd(String passowrd) {
         pref.set("passowrd",passowrd);
    }

    public static String getImage() {
        return pref.get("image",image);
    }

    public static void setImage(String image) {
         pref.set("image",image);
    }
      public static String getDescription() {
        return pref.get("description",description);
    }

    public static void setDescription(String description) {
         pref.set("description",description);
    }
      public static String getAddress() {
        return pref.get("address",address);
    }

    public static void setAddress(String address) {
         pref.set("address",address);
    }
      public static String getPhoneNumber() {
        return pref.get("phoneNumber",phoneNumber);
    }

    public static void setPhoneNumber(String phoneNumber) {
         pref.set("phoneNumber",phoneNumber);
    }

  
    
}
