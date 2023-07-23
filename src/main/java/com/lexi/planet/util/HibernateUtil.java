package com.lexi.planet.util;

import com.github.fluent.hibernate.cfg.scanner.EntityScanner;
import com.lexi.planet.entity.Hashtag;
import com.lexi.planet.entity.Post;
import com.lexi.planet.entity.Role;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lexi.planet.entity.User;

import java.util.Properties;

public class HibernateUtil {

   private static Logger logger = LoggerFactory.getLogger(HibernateUtil.class);

   private static SessionFactory sessionFactory;

   // static block to initialize the session factory
   static {
       try {
           sessionFactory = loadSessionFatory();
       } catch (Exception e) {
           logger.error("Exception while initializing hibernate util..., error={}",
                   e.getMessage());
       }
   }

   private static SessionFactory loadSessionFatory() {
        String dbDriver = "org.postgresql.Driver";
        String dbDialect = "org.hibernate.dialect.PostgreSQL9Dialect";
        String dbUrl = "jdbc:postgresql://localhost:5433/planet-DB";
        String dbUser = "lexiyin";
        String dbPassword = "pwd123";
//      Better NOT TO HARDCODE；

//       String[] modelPackages = {"com.lexi.planet.model"};
//       String dbDriver = System.getProperty("database.driver");
//       String dbDialect = System.getProperty("database.dialect");
//       String dbUrl = System.getProperty("database.url");
//       String dbUser = System.getProperty("database.user");
//       String dbPassword = System.getProperty("database.password");
//       // getProperty = retrieve system properties in VM option

       String[] modelPackages = {"com.lexi.planet.model"};


       Configuration configuration = new Configuration();
       configuration.addPackage("com.lexi.planet.model");
       configuration.addAnnotatedClass(User.class);
       configuration.addAnnotatedClass(Role.class);
       configuration.addAnnotatedClass(Post.class);
       configuration.addAnnotatedClass(Hashtag.class);
       // Because entity scanner does not work for me, I add the annotated class manually


       Properties settings = new Properties();
       // five essentials to link database and app
       settings.put(Environment.DRIVER, dbDriver);
       settings.put(Environment.URL, dbUrl);
       settings.put(Environment.USER, dbUser);
       settings.put(Environment.PASS, dbPassword);
       settings.put(Environment.DIALECT, dbDialect);
       settings.put(Environment.SHOW_SQL, "true"); //print the sql statement for convinient.
       settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread"); // how hibernate manages current session
       settings.put(Environment.HBM2DDL_AUTO, "validate"); //Important keyword: To validate if the @Column(name= ) and the column name are consistent
       configuration.setProperties(settings);

       EntityScanner.scanPackages(modelPackages).addTo(configuration); // scan the model package to find the model class
       StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
       ServiceRegistry serviceRegistry = registryBuilder.applySettings(configuration.getProperties()).build();
       SessionFactory localSessionFactory = configuration.buildSessionFactory(serviceRegistry);
       return localSessionFactory;
   }

   // SessionFactory is a singleton, so we use a static method to get it
   public static SessionFactory getSessionFactory() {
       return sessionFactory;
   }

   public static Session getSession() throws HibernateException {

       Session retSession=null;
       try {
           retSession = sessionFactory.openSession();
       }catch(Throwable t){
           logger.error("Exception while getting session.. ");
           t.printStackTrace();
       }
       if(retSession == null) {
           logger.error("session is discovered null");
       }

       return retSession;
   }

   /**
    * This main method is implemented as a testing point to verify your Hibernate configuration is correct
    * @param args
    */
   public static void main(String[] args) {
       SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
       logger.info("Success generate sessionFactory ={}", sessionFactory.toString());
       logger.info("Success generate sessionFactory, hashcode={}", sessionFactory.hashCode());
       Session session = sessionFactory.openSession();
       logger.info("The end");
       session.close();
   }

}
