package com.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;


public class HibernateUtil {
    private  static ServiceRegistry registry;
    private  static SessionFactory sessionFactory;

    private static void buildSessionFactory(){
        try {
            registry = new StandardServiceRegistryBuilder().configure().build();
            MetadataSources metadataSources = new MetadataSources(registry);
            Metadata metadata = metadataSources.getMetadataBuilder().build();
            sessionFactory =metadata.getSessionFactoryBuilder().build();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            buildSessionFactory();
        }
        return sessionFactory;
    }
    public static void shutdown(){
        if(registry != null){
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
