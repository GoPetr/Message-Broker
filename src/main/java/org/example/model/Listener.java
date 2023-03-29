package org.example.model;

import org.example.util.HibernateSessionFactory;

public class Listener {
    public static boolean flag;

    public static void checkSession(){
       if(HibernateSessionFactory.getSessionFactory() != null){
           flag = true;
       } else {
           flag = false;
       }
    }
}
