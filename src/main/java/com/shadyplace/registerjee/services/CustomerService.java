package com.shadyplace.registerjee.services;

import com.shadyplace.registerjee.models.Customer;
import com.shadyplace.registerjee.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class CustomerService {


    public CustomerService() {
    }

    public List<Customer> getCustomerByEmail(String email){
        SessionFactory sf = HibernateUtils.getSessionFactory();
        Session session = sf.getCurrentSession();
        Transaction tx = session.beginTransaction();

        List<Customer> customers = session.createQuery("FROM Customer c WHERE c.email = ?1", Customer.class)
                .setParameter(1, email)
                .getResultList();

        tx.commit();
        session.close();

        return customers;
    }

    public void registerCustomer(Customer customer){
        SessionFactory sf = HibernateUtils.getSessionFactory();
        Session session = sf.getCurrentSession();
        Transaction tx = session.beginTransaction();

        // Hasher le mot de passe utilisateur
        customer.setPassword(BCrypt.hashpw(customer.getPassword(), BCrypt.gensalt()));

        session.persist(customer);

        tx.commit();
        session.close();

    }
}
