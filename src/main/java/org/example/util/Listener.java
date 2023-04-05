package org.example.util;

public class Listener {
    public static boolean flag;

    public static void checkSession() {
        flag = HibernateSessionFactory.getSessionFactory() != null;
    }
}
