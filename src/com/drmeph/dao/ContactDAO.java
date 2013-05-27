package com.drmeph.dao;

import com.drmeph.entity.Contact;
import org.hibernate.criterion.Criterion;

import java.sql.SQLException;
import java.util.List;

public interface ContactDAO {
    void addContact(Contact contact) throws SQLException;
    void deleteContact(int id) throws SQLException;
    List<Contact> searchByCriteria(Criterion criterion) throws SQLException;
    Contact searchById(int id) throws SQLException;
    List<Contact> getListContact() throws SQLException;
    void updateContact(Contact contact) throws SQLException;
}
