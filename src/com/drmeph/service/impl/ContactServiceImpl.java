package com.drmeph.service.impl;

import com.drmeph.dao.ContactDAO;
import com.drmeph.entity.Contact;
import com.drmeph.service.ContactService;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service("contactService")
@Transactional(readOnly = true)
public class ContactServiceImpl implements ContactService{

    @Autowired
    ContactDAO contactDAO;

    @Override
    @Transactional(readOnly = false)
    public void addContact(Contact contact) throws SQLException {
        contactDAO.addContact(contact);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteContact(int id) throws SQLException {
        contactDAO.deleteContact(id);
    }

    @Override
    public List<Contact> findContactByName(String name) throws SQLException{
        Criterion query = Restrictions.or(Restrictions.like("firstName", "%"+name+"%"), Restrictions.like("lastName", "%"+name+"%"));

        return contactDAO.searchByCriteria(query);
    }

    @Override
    public Contact findContactById(int id) throws SQLException {
        return contactDAO.searchById(id);
    }

    @Override
    public List<Contact> getListContact() throws SQLException {
        return contactDAO.getListContact();
    }

    @Override
    @Transactional(readOnly = false)
    public void updateContact(Contact contact) throws SQLException {
        contactDAO.updateContact(contact);
    }
}
