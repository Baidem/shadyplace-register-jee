package com.shadyplace.registerjee.services;

import com.shadyplace.registerjee.models.Role;
import com.shadyplace.registerjee.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class RoleService {

    public Role getRoleByName(String roleName) {
        SessionFactory sf = HibernateUtils.getSessionFactory();
        Session session = sf.getCurrentSession();
        Transaction tx = session.beginTransaction();

        Role role = session.createQuery("FROM Role r WHERE r.roleName = ?1", Role.class)
                .setParameter(1, roleName)
                .getSingleResult();

        tx.commit();
        session.close();

        return role;


    }
}