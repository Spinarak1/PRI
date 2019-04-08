package com.software.rateit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class MyManager {

    public static void test() {

        System.out.println("Start");

        EntityManager entityManager = null;

        EntityManagerFactory entityManagerFactory = null;

        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-dynamic");
            entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();

            entityManager.getTransaction().commit();

            System.out.println("Done");

            entityManager.close();

        } catch (Throwable ex) {
            System.out.println("*********");
            ex.printStackTrace();
            System.out.println("****************************");
        } finally {
            if(entityManagerFactory != null)
                entityManagerFactory.close();
            else
                System.out.println("*****: entityManagerFactory == null");
        }

    }

}