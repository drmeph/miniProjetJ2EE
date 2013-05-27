package com.drmeph.controller;

import com.drmeph.entity.*;
import com.drmeph.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static com.drmeph.entity.PhoneType.*;

@Controller
public class UpdateContact extends MultiActionController {

    @Autowired
    private ContactService contactService;

    @RequestMapping(value = "/initListUp", method = RequestMethod.GET)
    public String list(ModelMap modelMap) {
        try {
            modelMap.addAttribute("listcontact", contactService.getListContact());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "updateContact";
    }

    @RequestMapping(value = "updatecontact", method = RequestMethod.POST)
    public String updatecontact(HttpServletRequest request,
                                HttpServletResponse response,
                                ModelMap modelMap) {

        int id = Integer.parseInt(request.getParameter("id"));
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String street = request.getParameter("street");
        String zip = request.getParameter("zip");
        String cc = request.getParameter("cc");

        String[] groups = request.getParameterValues("groups");

        HashMap<PhoneType, String> phoneMap = new HashMap<PhoneType, String>();

        if (!request.getParameter("homephone").equals("")) {
            phoneMap.put(HOME, request.getParameter("homephone"));
        }

        if (!request.getParameter("workphone").equals("")) {
            phoneMap.put(WORK, request.getParameter("workphone"));
        }

        if (!request.getParameter("cellphone").equals("")) {
            phoneMap.put(CELL, request.getParameter("cellphone"));
        }

        Contact contact = null;

        try {
            contact = contactService.findContactById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (!contact.getFirstName().equals(firstname)) contact.setFirstName(firstname);
        if (!contact.getFirstName().equals(firstname)) contact.setLastName(lastname);
        if (!contact.getEmail().equals(email)) contact.setEmail(email);

        if (!street.equals("") || !zip.equals("") || !cc.equals("")) {
            if (contact.getAdd() != null) {
                if (!contact.getAdd().getStreet().equals(street)) contact.getAdd().setStreet(street);
                if (!contact.getAdd().getZip().equals(zip)) contact.getAdd().setZip(zip);
                if (!contact.getAdd().getCountry().equals(cc)) contact.getAdd().setCountry(cc);
            } else {
                Address address = new Address();
                address.setStreet(street);
                address.setZip(zip);
                address.setCountry(cc);
                contact.setAdd(address);
            }
        }

        Set<PhoneNumber> newPhoneNumbers = new HashSet<PhoneNumber>();

        if (contact.getPhones() != null) { // if contact already have numbers in the db

            for (PhoneNumber phoneNumber : contact.getPhones()) {

                if (phoneMap.containsKey(phoneNumber.getType())){

                    if (!phoneNumber.getPhoneNumber().equals(phoneMap.get(phoneNumber.getType()))) {  // if number changed
                        phoneNumber.setPhoneNumber(phoneMap.get(phoneNumber.getType()));
                        newPhoneNumbers.add(phoneNumber);
                    } else { // if number didn't change
                        newPhoneNumbers.add(phoneNumber);
                    }
                    phoneMap.remove(phoneNumber.getType());
                }
            }

            if (phoneMap.size() > 0) { // if the number type isn't in the db yet
                for (PhoneType key : phoneMap.keySet()) {
                    PhoneNumber hp = new PhoneNumber();
                    hp.setContact(contact);

                    switch (key) {
                        case CELL:
                            hp.setType(CELL);
                            hp.setPhoneNumber(phoneMap.get(CELL));
                            break;
                        case WORK:
                            hp.setType(WORK);
                            hp.setPhoneNumber(phoneMap.get(WORK));
                            break;
                        default:
                            hp.setType(HOME);
                            hp.setPhoneNumber(phoneMap.get(CELL));
                    }

                    newPhoneNumbers.add(hp);
                }
            }
        } else {

            for (PhoneType key : phoneMap.keySet()) {
                PhoneNumber hp = new PhoneNumber();
                hp.setContact(contact);

                switch (key) {
                    case CELL:
                        hp.setType(CELL);
                        hp.setPhoneNumber(phoneMap.get(CELL));
                        break;
                    case WORK:
                        hp.setType(WORK);
                        hp.setPhoneNumber(phoneMap.get(WORK));
                        break;
                    default:
                        hp.setType(HOME);
                        hp.setPhoneNumber(phoneMap.get(CELL));
                }

                newPhoneNumbers.add(hp);
            }
        }

        if (newPhoneNumbers.size() > 0) {
            contact.setPhones(newPhoneNumbers);
        }

        if (groups != null) {
            Set<ContactGroup> newBook = new HashSet<ContactGroup>();

            if (contact.getBooks() != null) {

                for (String group : groups) {
                    Boolean included = false;

                    for (ContactGroup cg : contact.getBooks()) {

                        if (cg.getGroupName().equals(group)) {
                            newBook.add(cg);
                            included = true;
                            break;
                        }
                    }

                    if (!included) {
                        ContactGroup contactGroup = new ContactGroup();
                        contactGroup.setGroupName(group);
                        newBook.add(contactGroup);
                    }
                }

                contact.setBooks(newBook);
            } else {

                for (String group : groups){
                    ContactGroup contactGroup = new ContactGroup();
                    contactGroup.setGroupName(group);
                    newBook.add(contactGroup);
                }
                contact.setBooks(newBook);
            }
        }

        try {
            contactService.updateContact(contact);
        } catch (SQLException e) {
            e.printStackTrace();
            modelMap.addAttribute("message", "Error while updating "+
                    contact.getFirstName()+" "+contact.getLastName());
            return "accueil";
        }
        modelMap.addAttribute("message", "Successfully updated ");
        return "accueil";
    }

    @RequestMapping(value = "loadcontact", method = RequestMethod.POST)
    public String loadcontact(HttpServletRequest request,
                                HttpServletResponse response,
                                ModelMap modelMap) {

        int id = Integer.parseInt(request.getParameter("id"));

        try {
            Contact contact = contactService.findContactById(id);
            modelMap.addAttribute("contacttoupdate", contact);
        } catch (SQLException e) {
            e.printStackTrace();
            modelMap.addAttribute("message", "Error couldn't load contact :"+ id);
            return "accueil";
        }

        return "updateContact";
    }
}
