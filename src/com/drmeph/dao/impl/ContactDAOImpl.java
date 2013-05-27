package com.drmeph.dao.impl;

import com.drmeph.dao.ContactDAO;
import com.drmeph.entity.Contact;
import com.drmeph.entity.ContactGroup;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class ContactDAOImpl implements ContactDAO {

    @Autowired
    private SessionFactory sessionFactory;
    private List<Contact> res;

    @Override
    public void addContact(Contact contact) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        loadBooks(contact);
        session.saveOrUpdate(contact);
    }

    private void loadBooks(Contact contact) {
        Session session = sessionFactory.getCurrentSession();

        List<ContactGroup> aux = session.createCriteria(ContactGroup.class).list();
        Set<ContactGroup> books = new HashSet<ContactGroup>();

        for (ContactGroup contactGroup : contact.getBooks()) {
            Boolean updated = false;

            if (contactGroup.getGroupId() != 0) {
                books.add(contactGroup);
                continue;
            }

            for (ContactGroup cg : aux) {
                if (contactGroup.getGroupName().equals(cg.getGroupName())) {
                    books.add(cg);
                    updated = true;
                    break;
                }
            }

            if (!updated) {
                books.add(contactGroup);
            }
        }

        if (books.size() > 0) {
            contact.setBooks(books);
        }
    }

    @Override
    public void deleteContact(int id) throws SQLException {
        Session session = sessionFactory.getCurrentSession();

        res = session.createCriteria(Contact.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .add(Restrictions.eq("contactId", id)).list();

        if (res != null && res.size() == 1) {
            session.delete(res.get(0));
        } else {
            throw new SQLException("Error : Multiple iteration of id "+id);
        }
    }

    @Override
    public List<Contact> searchByCriteria(Criterion criterion) {
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(Contact.class);

        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .add(criterion);

        return criteria.list();
    }

    @Override
    public Contact searchById(int id) throws SQLException {
        return (Contact) sessionFactory.getCurrentSession().get(Contact.class, id);
    }

    @Override
    public List<Contact> getListContact() throws SQLException {
        Session session = sessionFactory.getCurrentSession();

        res = session.createCriteria(Contact.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return res;
    }

    @Override
    public void updateContact(Contact contact) throws SQLException {
        loadBooks(contact);
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(contact);
    }
}
