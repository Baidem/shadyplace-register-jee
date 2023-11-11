package com.shadyplace.registerjee.services;

import com.shadyplace.registerjee.models.FidelityRank;
import com.shadyplace.registerjee.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class FidelityRankService {

    public FidelityRank findByLabel(String label) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        String param = label;
        FidelityRank fidelityRank = session.createQuery(
                "FROM FidelityRank f WHERE f.label = :param", FidelityRank.class
        ).setParameter("param", param).getSingleResult();

        transaction.commit();
        session.close();

        return fidelityRank;
    }
}
