package com.drmeph.entity;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "PhoneNumber")
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int PhoneNumberId;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private PhoneType type;

    @NotNull
    private String phoneNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contactId")
    private Contact contact;

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public int getPhoneNumberId() {
        return PhoneNumberId;
    }

    public void setPhoneNumberId(int phoneNumberId) {
        PhoneNumberId = phoneNumberId;
    }

    public PhoneType getType() {
        return type;
    }

    public void setType(PhoneType type) {
        this.type = type;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
