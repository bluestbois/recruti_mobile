/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.recrutini.gui;

import com.codename1.io.Preferences;

/**
 *
 * @author Lenovo
 */
public class SessionManagerC {
    
    public static Preferences pref ; // 3ibara memoire sghira nsajlo fiha data 
    
    
    
    // hethom données ta3 user lyt7b tsajlhom fi session  ba3d login 
    private static int id ; 
    
    private static String firstname ; 
    private static String lastname ; 
    private static String email; 
    private static String password ;
    private static String image;
    private static String gender   ; 
    private static String nationality;
    private static String phoneNumber;
    private static String address;
    private static String LOE;
    private static String experience;

    public static Preferences getPref() {
        return pref;
    }

    public static void setPref(Preferences pref) {
        SessionManagerC.pref = pref;
    }

    public static int getId() {
        return pref.get("id",id);// kif nheb njib id user connecté apres njibha men pref 
    }

    public static void setId(int id) {
        pref.set("id",id);//nsajl id user connecté  w na3tiha identifiant "id";
    }

    public static String getFirstName() {
        return pref.get("firstname",firstname);
    }

    public static void setFirstName(String firstname) {
         pref.set("firstname",firstname);
    }
     public static String getLastName() {
        return pref.get("lastname",lastname);
    }

    public static void setLastName(String lastname) {
         pref.set("lastname",lastname);
    }

    public static String getEmail() {
        return pref.get("email",email);
    }

    public static void setEmail(String email) {
         pref.set("email",email);
    }

    public static String getPassword() {
        return pref.get("password",password);
    }

    public static void setPassword(String password) {
         pref.set("passowrd",password);
    }
    
        public static String getGender() {
        return pref.get("gender",gender);
    }

    public static void setGender(String gender) {
         pref.set("gender",gender);
    }

        public static String getNationality() {
        return pref.get("nationality",nationality);
    }

    public static void setNationality(String nationality) {
         pref.set("nationality",nationality);
    }
    
        public static String getPhoneNumber() {
        return pref.get("phonenumber",phoneNumber);
    }

    public static void setPhoneNumber(String phoneNumber) {
         pref.set("phonenumber",phoneNumber);
    }
    
        public static String getAddress() {
        return pref.get("address",address);
    }

    public static void setAddress(String address) {
         pref.set("address",address);
    }
    
        public static String getLOE() {
        return pref.get("LOE",LOE);
    }

    public static void setLOE(String LOE) {
         pref.set("LOE",LOE);
    }
    
        public static String getExperience() {
        return pref.get("experience",experience);
    }

    public static void setExperience(String experience) {
         pref.set("experience",experience);
    }
    
    public static String getImage() {
        return pref.get("image",image);
    }

    public static void setImage(String image) {
         pref.set("image",image);
    }
    
    
    
    
    
    
}
