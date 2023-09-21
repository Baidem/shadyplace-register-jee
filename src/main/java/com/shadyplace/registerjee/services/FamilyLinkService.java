package com.shadyplace.registerjee.services;

import com.shadyplace.registerjee.models.FamilyLink;
import com.shadyplace.registerjee.models.enums.FamilyLinkLabel;
import com.shadyplace.registerjee.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class FamilyLinkService {


    public FamilyLink getFamilyLinkByLabel(FamilyLinkLabel label) {
        SessionFactory sf = HibernateUtils.getSessionFactory();
        Session session = sf.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        String param = label.toString();
        FamilyLink familyLink = session.createQuery(
                "FROM FamilyLink f WHERE f.label = :param",FamilyLink.class
        ).setParameter("param", FamilyLinkLabel.valueOf(param)).getSingleResult();
        transaction.commit();
        session.close();

        return familyLink;
    }


}