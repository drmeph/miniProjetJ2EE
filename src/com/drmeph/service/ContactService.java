package com.drmeph.service;

import com.drmeph.entity.Contact;

import java.sql.SQLException;
import java.util.List;

public interface ContactService {
    void addContact(Contact contact) throws SQLException;
    void deleteContact(int id) throws SQLException;
    List<Contact> findContactByName(String name) throws SQLException;
    Contact findContactById(int id) throws  SQLException;
    List<Contact> getListContact() throws SQLException;
    void updateContact(Contact contact) throws SQLException;
}
