package com.drmeph.entity;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Set;

@Entity
@Table(name = "Contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contactId;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId")
    private Address add;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<PhoneNumber> phones;

    @ManyToMany(targetEntity = ContactGroup.class,
        cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE},
        fetch = FetchType.EAGER)
    @JoinTable(name = "ContactGroupMapper",
    joinColumns = @JoinColumn(name = "contactId"),
    inverseJoinColumns = @JoinColumn(name = "contactGroupId"))
    private Set<ContactGroup> books;

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public Set<ContactGroup> getBooks() {
        return books;
    }

    public void setBooks(Set<ContactGroup> books) {
        this.books = books;
    }

    public Set<PhoneNumber> getPhones() {
        return phones;
    }

    public void setPhones(Set<PhoneNumber> phones) {
        this.phones = phones;
    }

    public Address getAdd() {
        return add;
    }

    public void setAdd(Address add) {
        this.add = add;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
