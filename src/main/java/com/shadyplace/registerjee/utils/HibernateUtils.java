package com.shadyplace.registerjee.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static  final SessionFactory sf = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return  new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Impossible de créer la session Factory" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory(){
        return sf;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
