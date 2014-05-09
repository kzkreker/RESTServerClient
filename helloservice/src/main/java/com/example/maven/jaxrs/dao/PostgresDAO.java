/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.maven.jaxrs.dao;

import com.example.maven.jaxrs.personservice.mapping.Person;
import com.example.maven.jaxrs.personservice.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author kreker
 */
public class PostgresDAO {
   
    /*
    * Данная функция добовляет нового пользователя
    */ 
      public Long addPerson(String FirstName, String LastName, int Age) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            Person p1 = new Person();
            p1.setFirstName(FirstName);
            p1.setLastName(LastName);
            p1.setAge(Age);
            Long personId = (Long)session.save(p1);
            session.getTransaction().commit();

            Person person = (Person) session.get(Person.class, personId);
            System.out.println(person);
            session.close();

            return personId;
       }
       catch (Exception e)
       {
           return Long.valueOf(0);
       }
    }
    /*
     *Вывод списка созданных пользователей
     */  
      
      public List<Person>  getAllPerson() {
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Query q = session.createQuery("from Person");
                     
            List<Person> persons = q.list();
            session.close();
            return persons; 
       }
       catch (Exception e)
       {
           return null;
       }
    }
    /*
    * Данная функция обновляет пользователя по его идентификатору
    */ 
    public Boolean updatePerson(String FirstName, String LastName, int Age, long Person_Id) {
       try { 
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            Query q = session.createQuery("from Person where Person_ID = :Person_Id");
            q.setParameter("Person_Id", Person_Id);
            Person person = (Person)q.list().get(0);

            person.setAge(Age);
            person.setFirstName(FirstName);
            person.setLastName(LastName);
            person.setId(Person_Id);
            session.saveOrUpdate(person);
            session.flush();
            session.close();
            return Boolean.TRUE;
       }
       catch (Exception e)
       {
           return Boolean.FALSE;
       }
    }
   /*
    * Данная функция возвращает пользователя по его идентификатору
    */
    public Person returnPersonById(long Person_Id) {
        try { 
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            Query q = session.createQuery("from Person where Person_ID = :Person_Id");
            q.setParameter("Person_Id", Person_Id);
            Person person = (Person)q.list().get(0);
            session.close();        
            return person;
       }
       catch (Exception e)
       {
           System.out.println(e.toString());
           return null;
       }
    }     
   
   /*
    * Данная функция удаляет пользователя по его идентификатору
    */
    public Boolean deletePersonById(long Person_Id) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            Query q = session.createQuery("from Person where Person_ID = :Person_Id");
            q.setParameter("Person_Id",Person_Id);
            Person person = (Person)q.list().get(0);
            session.delete(person);
            session.flush();
            session.close();        
            return Boolean.TRUE;
       }
       catch (Exception e)
       {
           return Boolean.FALSE;
       }
        
    }
}
