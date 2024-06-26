package com.shadyplace.registerjee.services;

import com.shadyplace.registerjee.models.User;
import com.shadyplace.registerjee.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class UserService {


    private RoleService roleService;

    public UserService() {
        this.roleService = new RoleService();
    }

    public List<User> getCustomerByEmail(String email){
        SessionFactory sf = HibernateUtils.getSessionFactory();
        Session session = sf.getCurrentSession();
        Transaction tx = session.beginTransaction();

        List<User> customers = session.createQuery("FROM User c WHERE c.email = ?1", User.class)
                .setParameter(1, email)
                .getResultList();

        tx.commit();
        session.close();

        return customers;
    }

    public void registerCustomer(User user){
        user.addRole(this.roleService.getRoleByName("USER"));

        SessionFactory sf = HibernateUtils.getSessionFactory();
        Session session = sf.getCurrentSession();
        Transaction tx = session.beginTransaction();

        // Hasher le mot de passe utilisateur
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));

        session.persist(user);

        tx.commit();
        session.close();

    }
}
