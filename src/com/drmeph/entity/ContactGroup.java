package com.drmeph.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ContactGroup")
public class ContactGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int groupId;

    private String groupName;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE},
    mappedBy = "books",
    targetEntity = Contact.class,
    fetch = FetchType.LAZY)
    private Set<Contact> contacts;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }
}
